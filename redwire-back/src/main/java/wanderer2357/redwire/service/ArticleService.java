package wanderer2357.redwire.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import wanderer2357.redwire.dto.ArticleDto;
import wanderer2357.redwire.exception.ArticleNotFoundException;
import wanderer2357.redwire.exception.InvalidSaveArticleRequestException;
import wanderer2357.redwire.mapper.ArticleDtoMapper;
import wanderer2357.redwire.mapper.ArticleEntityMapper;
import wanderer2357.redwire.repository.ArticleRepository;
import wanderer2357.redwire.model.ArticleEntity;

@Service
@Validated
public class ArticleService {
	
	private static final Logger log = LoggerFactory.getLogger(ArticleService.class);
	private final ArticleRepository articleRepository;
	private final ArticleDtoMapper articleDtoMapper;
	private final ArticleEntityMapper articleEntityMapper;
	public ArticleService(ArticleRepository articleRepository,
			ArticleDtoMapper articleDtoMapper,
			ArticleEntityMapper articleEntityMapper) {
		this.articleRepository = articleRepository;
		this.articleDtoMapper = articleDtoMapper;
		this.articleEntityMapper = articleEntityMapper;
	}
	
	public Page<ArticleDto> getArticlePage(Pageable pageable) {
		return articleRepository
				.findAll(pageable)
				.map(articleDtoMapper);
	}
	
	public ArticleDto getArticleById(Long id) {
		return articleRepository.findById(id)
				.map(articleDtoMapper)
				.orElseThrow(() -> {
					log.warn("Client ID {} not found", id);
					throw new ArticleNotFoundException("Could not find specified article (id: " + id +")");
				});
	}
	
	public ArticleDto getArticleByCode(String code) {
		List<ArticleDto> articleDtoList = articleRepository.findByCode(code)
				.stream()
				.map(articleDtoMapper)
				.toList();
		
		if(articleDtoList.isEmpty()) {
			log.warn("Article code not found");
			throw new ArticleNotFoundException("Could not find specified article (code: " + code + ")");
		}
		
		return articleDtoList.get(0);
	}
	
	public ArticleDto saveArticle(@Valid ArticleDto articleDto) {
		
		if (articleRepository.existsByCode(articleDto.getCode())) {
			log.warn("Aborting article save: code already in use");
			throw new InvalidSaveArticleRequestException("Code already in use");
		}
		
		ArticleEntity savedArticleEntity = articleRepository.save(articleEntityMapper.apply(articleDto));
		ArticleDto savedArticleDto = articleDtoMapper.apply(savedArticleEntity);
		log.debug("Saved new article with id {}", savedArticleDto.getId());
		
		return savedArticleDto;
	}

}

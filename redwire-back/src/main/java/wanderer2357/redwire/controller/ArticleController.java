package wanderer2357.redwire.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wanderer2357.redwire.dto.ArticleDto;
import wanderer2357.redwire.payload.RedwireResponsePayload;
import wanderer2357.redwire.service.ArticleService;

@RestController
@Validated
@RequestMapping("/articles")
public class ArticleController {
	
	private final ArticleService articleService;
	
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@GetMapping
	public ResponseEntity<RedwireResponsePayload<Page<ArticleDto>>>
	getArticlePage(@PageableDefault(size=10, sort="code") Pageable pageable) {
		
		Page<ArticleDto> articleDtoPage = articleService.getArticlePage(pageable);
		
		RedwireResponsePayload<Page<ArticleDto>> payload = new RedwireResponsePayload<Page<ArticleDto>>
		(HttpStatus.OK, 
		"Fetched article page", 
		articleDtoPage);
		
		return ResponseEntity.ok(payload);
	}
	
	@GetMapping("{id}/id")
	public ResponseEntity<RedwireResponsePayload<ArticleDto>> getArticleById(@PathVariable Long id) {
		
		ArticleDto articleDto = articleService.getArticleById(id);
		
		RedwireResponsePayload<ArticleDto> payload = new RedwireResponsePayload<ArticleDto>
		(HttpStatus.OK, 
		"Fetched article using id", 
		articleDto);
		
		return ResponseEntity.ok().body(payload);
	}
	
	@GetMapping("{code}/code")
	public ResponseEntity<RedwireResponsePayload<ArticleDto>> getArticleByCode(@PathVariable String code) {
		
		ArticleDto articleDto = articleService.getArticleByCode(code);
		
		RedwireResponsePayload<ArticleDto> payload = new RedwireResponsePayload<ArticleDto>
		(HttpStatus.OK, 
		"Fetched article using code", 
		articleDto);
		
		return ResponseEntity.ok().body(payload);
	}
	
	@PostMapping
	public ResponseEntity<RedwireResponsePayload<ArticleDto>> saveArticle(@RequestBody ArticleDto articleDto) {
		
		ArticleDto savedArticleDto = articleService.saveArticle(articleDto);
		
		RedwireResponsePayload<ArticleDto> payload = new RedwireResponsePayload<ArticleDto>
		(HttpStatus.CREATED,
		"Saved article",
		savedArticleDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(payload);
	}
	

}

package com.hyojin.OneDayOneCommit.configuration;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.resource.ResourceResolver;
import org.springframework.web.reactive.resource.ResourceResolverChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class WebConfiguration implements WebFluxConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    ResourceResolver resolver = new ReactResourceResolver();

    registry.addResourceHandler("/swagger-ui.html**")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");

    registry.addResourceHandler("/api/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");

    registry.addResourceHandler("**")
        .addResourceLocations("/public/")
        .resourceChain(true)
        .addResolver(resolver);
  }

  public class ReactResourceResolver implements ResourceResolver {

    private static final String REACT_DIR = "/static/";
    private static final String REACT_STATIC_DIR = "static";

    private Resource index = new ClassPathResource(REACT_DIR + "index.html");
    private List<String> rootStaticFiles = Arrays.asList("favicon.io",
        "asset-manifest.json", "manifest.json", "service-worker.js");
    private List<String> ignoredPaths = Arrays.asList("api");


    @Override
    public Mono<Resource> resolveResource(ServerWebExchange exchange, String requestPath,
        List<? extends Resource> locations, ResourceResolverChain chain) {
      return Mono.just(resolve(requestPath, locations));
    }

    @Override
    public Mono<String> resolveUrlPath(String resourcePath, List<? extends Resource> locations,
        ResourceResolverChain chain) {
      Resource resolvedResource = resolve(resourcePath, locations);
      if (resolvedResource == null) {
        return null;
      }
      try {
        return Mono.just(resolvedResource.getURL().toString());
      } catch (IOException e) {
        return Mono.just(resolvedResource.getFilename());
      }
    }

    private Resource resolve(String requestPath, List<? extends Resource> locations) {
      if (requestPath == null) {
        return null;
      }
      if (rootStaticFiles.contains(requestPath)
          || requestPath.startsWith(REACT_STATIC_DIR)) {
        return new ClassPathResource(REACT_DIR + requestPath);
      } else {
        return index;
      }
    }

  }
}
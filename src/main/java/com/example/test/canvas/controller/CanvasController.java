package com.example.test.canvas.controller;

import com.example.test.canvas.entity.TokenResponse1;
import com.example.test.canvas.service.CanvasService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/")
@Log4j2
public class CanvasController {
    @Autowired
    private CanvasService canvasService;
    @Autowired
    private OAuth2AuthorizedClientRepository authorizedClientRepository;


    @Autowired
    public CanvasController(CanvasService canvasService, OAuth2AuthorizedClientRepository authorizedClientRepository) {
        this.canvasService = canvasService;
        this.authorizedClientRepository = authorizedClientRepository;
    }

    @GetMapping("welcome")
    public String setupCanvasAccount(HttpServletRequest request, @RequestParam("code") String code) throws Exception {
        canvasService.fetchAccessToken(code);
        return "/templates/canvas-setup.html";
    }

    @PostMapping("canvas/start")
    public String startSync(@RegisteredOAuth2AuthorizedClient("canvas") OAuth2AuthorizedClient authorizedClient) throws IOException {
        // Set the OAuth2 access token in the RestTemplate
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        canvasService.setAccessToken(accessToken);

        // Trigger the sync process
        // canvasService.syncClassRosters(TokenResponse1
        // tokenResponse1);

        // Redirect to a success page or return a success message
        return "sync-success";
    }

    @GetMapping("callback")
    public String handleOAuthCallback(Authentication authentication) {
        OAuth2AuthorizedClient authorizedClient = (OAuth2AuthorizedClient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        authorizedClientRepository.saveAuthorizedClient(authorizedClient, authentication, null, null);
        return "redirect:/canvas/sync/start";
    }

    @GetMapping("test")
    public String test(Authentication authentication) {
        log.info("inside TEST !!!!");
        return "TEST";
    }
}

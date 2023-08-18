package com.boot.casbin.controller;

import com.boot.casbin.model.Data;
import com.boot.casbin.service.DataService;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * 测试权限入口
 *
 * @author lizhifu
 * @since 2023/8/18
 */
@RestController
@RequestMapping("/data")
@Slf4j
public class DataController {
    private final DataService dataService;
    @Inject
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/users/all")
    public ResponseEntity<Data> getForUsersData(Authentication authentication) {
        log.info("getData: authentication={}", authentication.getName());
        authentication.getAuthorities().forEach(a -> {
            log.info("  authority={}", a.getAuthority());
        });
        return ResponseEntity.ok().body(
                dataService.getSecuredData("Secured for USER/ADMIN " + authentication.getName()));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admins/all")
    public ResponseEntity<Data> getDataForAdmins(Authentication authentication) {
        log.info("getData: authentication={}", authentication.getName());
        authentication.getAuthorities().forEach(a -> {
            log.info("  authority={}", a.getAuthority());
        });
        return ResponseEntity.ok()
                .body(dataService.getSecuredData("Secured for ADMIN " + authentication.getName()));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/admins/state/{state}")
    public ResponseEntity<Data> setStateForAdmins(Authentication authentication,
                                                  @PathVariable String state) {
        log.info("setState: authentication={} state={}", authentication.getName(), state);
        authentication.getAuthorities().forEach(a -> {
            log.info("  authority={}", a.getAuthority());
        });
        dataService.setState(state);
        return ResponseEntity.ok().build();
    }

}

package inteca.task.FamilyApp.controller;

import inteca.task.FamilyApp.model.api.Request.FamilyRequest;
import inteca.task.FamilyApp.model.api.Response.FamilyResponse;
import inteca.task.FamilyApp.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/family")
public class FamilyController {
    private final FamilyService familyService;

    @GetMapping(value = "/get/{familyId}", produces = "application/json")
    public FamilyResponse getFamily(@PathVariable int familyId) {
        return familyService.getFamily(familyId);
    }


    @PostMapping(value = "/post")
    public int createFamily(@RequestBody FamilyRequest familyRequest) {
        return familyService.createFamily(familyRequest);
    }
}

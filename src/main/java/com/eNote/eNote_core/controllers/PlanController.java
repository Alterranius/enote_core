package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.interfaces.PlanManager;
import com.eNote.eNote_core.interfaces.PlansShower;
import com.eNote.eNote_core.models.Plan;
import com.eNote.eNote_core.models.PlanContent;
import com.eNote.eNote_core.models.PlanType;
import com.eNote.eNote_core.services.plan.util.PlanContentValidator;
import com.eNote.eNote_core.services.plan.util.PlanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanManager planManager;
    private final PlansShower plansShower;
    private final PlanValidator planValidator;
    private final PlanContentValidator planContentValidator;
    private final UserTarget userTarget;

    @Autowired
    public PlanController(PlanManager planManager, PlansShower plansShower, PlanValidator planValidator, PlanContentValidator planContentValidator, UserTarget userTarget) {
        this.planManager = planManager;
        this.plansShower = plansShower;
        this.planValidator = planValidator;
        this.planContentValidator = planContentValidator;
        this.userTarget = userTarget;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createPlan(@RequestBody Plan plan, BindingResult bindingResult) {
        planValidator.validate(plan, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        planManager.create(plan);
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePlan(@RequestBody Plan plan,
                                                    @PathVariable("id") int id,
                                                    BindingResult bindingResult) {
        planValidator.validate(plan, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        planManager.update(id, plan);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePlan(@PathVariable("id") int id) {
        try {
            planManager.delete(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/content/{id}")
    public ResponseEntity<HttpStatus> createPlanContent(@RequestBody PlanContent planContent,
                                                        @PathVariable("id") int id,
                                                        BindingResult bindingResult) {
        planContentValidator.validate(planContent, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        planManager.addContent(id, planContent);
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @PutMapping("/content/{id}")
    public ResponseEntity<HttpStatus> updatePlanContent(@RequestBody PlanContent planContent,
                                                 @PathVariable("id") int id) {
        planManager.updateContent(id, planContent);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/content/{id}")
    public ResponseEntity<HttpStatus> deletePlanContent(@PathVariable("id") int id) {
        try {
            planManager.removeContent(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    @ResponseBody
    public List<Plan> getProjectPlans(@PathVariable("id") int id) {
        try {
            return plansShower.getProjectPlans(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/depart/{id}")
    @ResponseBody
    public List<Plan> getDepartPlans(@PathVariable("id") int id) {
        try {
            return plansShower.getDepartPlans(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<PlanType> getPlanTypes() {
        try {
            return plansShower.getPlanTypes();
        } catch (Exception e) {
            return null;
        }
    }
}

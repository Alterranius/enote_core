package com.eNote.eNote_core.services.plan;

import com.eNote.eNote_core.interfaces.PlanManager;
import com.eNote.eNote_core.interfaces.PlansShower;
import com.eNote.eNote_core.models.Plan;
import com.eNote.eNote_core.models.PlanContent;
import com.eNote.eNote_core.models.PlanType;
import com.eNote.eNote_core.repositories.postgres.PlanContentRepository;
import com.eNote.eNote_core.repositories.postgres.PlanRepository;
import com.eNote.eNote_core.repositories.postgres.PlanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Service
public class PlanService implements PlanManager, PlansShower {
    private final PlanRepository planRepository;
    private final PlanTypeRepository planTypeRepository;
    private final PlanContentRepository planContentRepository;
    @Autowired
    public PlanService(PlanRepository planRepository, PlanTypeRepository planTypeRepository, PlanContentRepository planContentRepository) {
        this.planRepository = planRepository;
        this.planTypeRepository = planTypeRepository;
        this.planContentRepository = planContentRepository;
    }

    @Override
    public String create(Plan plan) {
        plan.setType(planTypeRepository.findById(plan.getType().getId()).get());
        planRepository.save(plan);
        return "Сохранено";
    }

    @Override
    public String update(int id, Plan plan) {
        Optional<Plan> currentPlan = planRepository.findById(id);
        if (currentPlan.isPresent()) {
            currentPlan.get().setAim(plan.getAim());
            currentPlan.get().setName(plan.getName());
            currentPlan.get().setType(plan.getType());
            planRepository.save(currentPlan.get());
            return "Сохранено";
        } else {
            return "Неактуальный план";
        }
    }

    @Override
    public String delete(int id) {
        Optional<Plan> currentPlan = planRepository.findById(id);
        if (currentPlan.isPresent()) {
            planRepository.delete(currentPlan.get());
            return "Удалено";
        } else {
            return "Неактуальный план";
        }
    }

    @Override
    public String addContent(int id, PlanContent planContent) {
        Optional<Plan> currentPlan = planRepository.findById(id);
        if (currentPlan.isPresent()) {
            currentPlan.get().getContent().add(planContent);
            planContent.setPlan(currentPlan.get());
            planContentRepository.save(planContent);
            planRepository.save(currentPlan.get());
            return "Сохранено";
        } else {
            return "Неактуальный план";
        }
    }

    @Override
    public String removeContent(int id) {
        Optional<PlanContent> content = planContentRepository.findById(id);
        if (content.isPresent()) {
             content.get().setPlan(null);
             planContentRepository.delete(content.get());
             return "Удалено";
        } else {
            return "Неактуальный план";
        }
    }

    @Override
    public String updateContent(int id, PlanContent planContent) {
        Optional<Plan> currentPlan = planRepository.findById(id);
        if (currentPlan.isEmpty()) {
            return "Неактуальный план";
        }
        PlanContent currentContent = new PlanContent();
        if (currentPlan.get().getContent() == null) {
            currentPlan.get().setContent(new ArrayList<>());
        }
        if (currentPlan.get().getContent().stream().anyMatch(c -> c.getId() == planContent.getId())) {
            currentContent = planContent;
        }
        currentContent.setAim(planContent.getAim());
        currentContent.setName(planContent.getName());
        currentContent.setDescription(planContent.getDescription());
        currentContent.setPosition(planContent.getPosition());
        currentContent.setPlan(currentPlan.get());
        planContentRepository.save(currentContent);
        planRepository.save(currentPlan.get());
        return "Сохранено";
    }

    @Override
    public Optional<Plan> getPlanDetails(Plan plan) {
        return planRepository.findById(plan.getId());
    }

    @Override
    public List<Plan> getProjectPlans(int id) {
        return planRepository.findPlansByProject_Id(id);
    }

    @Override
    public List<Plan> getDepartPlans(int id) {
        return planRepository.findPlansByDepart_Id(id);
    }

    @Override
    public List<PlanType> getPlanTypes() {
        return planTypeRepository.findAll();
    }
}

package tn.veganet.reclamation.service;


import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.veganet.reclamation.dao.DemandeRepository;
import tn.veganet.reclamation.entities.CamundaUserConfiguration;
import tn.veganet.reclamation.entities.Demande;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DemandeService {
    private static final Logger log = LoggerFactory.getLogger(DemandeService.class);

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public DemandeService(RuntimeService runtimeService, TaskService taskService) {
        //super();
        this.runtimeService = runtimeService;
    this.taskService = taskService;
    }

    public void traitement(DelegateExecution execution) {
        Map<String, Object> vars=execution.getVariables();

        log.info("+++++++++++++++++++++Traité++++++");

    }
    public void confirmation(DelegateExecution execution) {
        Map<String, Object> vars=execution.getVariables();



        log.info("+++++++++++++++++++++Confirmé++++++");

    }
    public void valide(DelegateExecution execution) {
        Map<String, Object> vars=execution.getVariables();


        log.info("+++++++++++++++++++++Validé++++++");

    }
}

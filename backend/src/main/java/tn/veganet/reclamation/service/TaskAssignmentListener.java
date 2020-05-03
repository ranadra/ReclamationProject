package tn.veganet.reclamation.service;

import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.context.Context;
import org.apache.commons.mail.Email;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskAssignmentListener implements TaskListener {
    private static final String HOST = "smtp.gmail.com";
    private static final String USER = "rana.draouil95@gmail.com";
    private static final String PWD = "rana95**";
    private static final String port = "587";
    private final static Logger LOGGER = Logger.getLogger(TaskAssignmentListener.class.getName());
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DemandeService.class);
    /*public void claim(DelegateTask delegateTask){
        String assignee = delegateTask.getAssignee();
        String taskId = delegateTask.getId();
        TaskService taskService = Context.getProcessEngineConfiguration().getTaskService();
            taskService.claim(taskId,assignee);
    }*/
    public void notify(DelegateTask delegateTask) {
        AuthorizationService authorizationService = delegateTask.getProcessEngineServices().getAuthorizationService();
        Authorization authorization = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
        authorization.setUserId(delegateTask.getAssignee());
        authorization.setResource(Resources.TASK);
        authorization.addPermission(Permissions.TASK_WORK);
        authorization.addPermission(Permissions.UPDATE_TASK);
        authorization.setResourceId(delegateTask.getId());
        authorizationService.saveAuthorization(authorization);

        String assignee = delegateTask.getAssignee();
        log.info("++++++++"+assignee+"++++++++");

        String taskId = delegateTask.getId();
        log.info("++++++++"+taskId+"++++++++");
        if (assignee != null) {

            // Get User Profile from User Management
            IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
            User user = identityService.createUserQuery().userId(assignee).singleResult();
            log.info("++++++++"+user+"++++++++");
            if (user != null ) {

                // Get Email Address from User Profile
                String recipient = user.getEmail();

                log.info("+++++++++++"+recipient+"++++++++++");
                if (recipient != null && !recipient.isEmpty()) {


                    Email email = new SimpleEmail();
                    email.setHostName(HOST);
                    email.setAuthentication(USER, PWD);
                    email.setTLS(true);

                    try {
                        email.setFrom("noreply@camunda.org");
                        email.setSubject("Task assigned: " + delegateTask.getName());
                        email.setMsg("Please complete: http://localhost:8002/app/tasklist/default/#/task/" + taskId);

                        email.addTo(recipient);

                        email.send();
                        LOGGER.info("Task Assignment Email successfully sent to user '" + assignee + "' with address '" + recipient + "'.");

                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
                    }

                } else {
                    LOGGER.warning("Not sending email to user " + assignee + "', user has no email address.");
                }

            } else {
                LOGGER.warning("Not sending email to user " + assignee + ", user is not enrolled with identity service.");
            }


        }

    }



}

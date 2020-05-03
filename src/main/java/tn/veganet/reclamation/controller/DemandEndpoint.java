package tn.veganet.reclamation.controller;
import io.swagger.annotations.*;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tn.veganet.reclamation.dao.DemandeRepository;
import tn.veganet.reclamation.dao.EtatRepository;
import tn.veganet.reclamation.entities.Demande;
import tn.veganet.reclamation.entities.Etat;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin
@RestController
public class DemandEndpoint {
    private static final Logger log = LoggerFactory.getLogger(DemandEndpoint.class);
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private  final ProcessEngine processEngine;
@Autowired
     private DemandeRepository demandeRep;
    public DemandEndpoint(RuntimeService runtimeService, TaskService taskService, ProcessEngine processEngine ) {
        this.processEngine = processEngine;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }
@Autowired
private EtatRepository etatRep;
    @ApiOperation(value = "View a list of available requests", authorizations = {@Authorization("basicAuth")}, response = List.class)
 @GetMapping(value="/demandes")
 public List<Demande> getList () {
     List <Demande> dems = demandeRep.findAll();
     return dems;
 }
    @PostMapping(value = "/addEtat",produces =MediaType.APPLICATION_JSON_VALUE)
   public  Etat savEtat(@RequestBody Etat etat){

        etatRep.save(etat);
        return etat;
    }

            @GetMapping(value="/index")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value="/form" ,produces =MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView formDemande (Model model) {
     model.addAttribute("demande", new
             Demande());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("formdemande");
        return modelAndView;
    }
  @PostMapping(value = "/add" , produces = APPLICATION_JSON_VALUE)
  public Demande save( @RequestBody Demande demande) {
      if (demande.getEtat() != null && demande.getEtat().getId_etat() != null) {
          demande.getEtat();

      } else {
         Etat etat = etatRep.findByCode("new");
          demande.setEtat(etat);
      }

//      ProcessInstance processInstanceId= runtimeService.startProcessInstanceByKey("proc" );
//      log.info("+++++++++++++++++++++++++++++"+processInstanceId.getId()+"+++++++++++++++++++++++++++++");
//      String processId = processInstanceId.getId();
//      demande.setIdProcess(processId);
      demandeRep.save(demande);
      log.info("+++++++"+demande.getEtat().getCode()+"++++");

      return demande;

  }
  @GetMapping(value = "/demandesEncours")
  public ModelAndView demandEncours(Model model){
      List<Demande> dem = demandeRep.findByCode("wait");
      model.addAttribute("demandes", dem);
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.setViewName("listdemandeEncours");
      return modelAndView;

  }
    @PostMapping(value = "/demandes/{id_demande}")
public ModelAndView encoursEtat(@PathVariable("id_demande") UUID uuid ){

            Demande dem = demandeRep.findById(uuid).get();
            Etat etat = etatRep.findByCode("wait");
            dem.setEtat(etat);
            demandeRep.save(dem);
            completeTask(dem.getIdProcess());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/demandesEncours");
        return modelAndView;
 }
    public void claimTask(String instanceId) {
        log.info("+++"+instanceId+"+++");
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(instanceId).active().initializeFormKeys()
                .list();
        log.info("+++"+taskList+"");
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + taskList.toString() + "+++++++");
        if (taskList != null && !taskList.isEmpty())
            for (Task task : taskList)
                taskService.claim(task.getId(),task.getAssignee());
    }

    public void completeTask(String instanceId) {
        log.info("+++"+instanceId+"+++");
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(instanceId).active().initializeFormKeys()
                .list();
        log.info("+++"+taskList+"");
        log.info("+++++++++++++++" + taskList.toString() + "+++++++");
       IdentityService identityService = processEngine.getIdentityService();
        String currentUser = "rana1";
        Authentication authentication = new Authentication(currentUser,null);
        identityService.setAuthentication(authentication);
        if (taskList != null && !taskList.isEmpty())
            for (Task task : taskList) {
                processEngine.getTaskService().claim(task.getId(),task.getAssignee());
                log.info(task.getAssignee());
                processEngine.getTaskService().complete(task.getId());
                //taskService.complete(task.getId());
            }
    }
    @GetMapping(value = "/demandesValides")
    public ModelAndView demandValides(Model model){

        List<Demande> dem = demandeRep.findByCode("success");
        model.addAttribute("demandes", dem);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("listdemandeValides");
        return modelAndView;

    }
    @PostMapping(value = "/demandesEncours/{id_demande}")
    public ModelAndView valideEtat(@PathVariable("id_demande") UUID uuid){
        Demande dem = demandeRep.findById(uuid).get();
        log.info("+++++++"+dem.getEtat().getCode()+"++++");
        Etat etat = etatRep.findByCode("success");
        dem.setEtat(etat);
        demandeRep.save(dem);
        completeTask(dem.getIdProcess());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/demandesValides");
        return modelAndView;
 }
    /*PostMapping(value = "/demandesAnnule/{id_demande}")
    public ModelAndView annulEtat(@PathVariable("id_demande") UUID uuid){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }*/

    @GetMapping(value = "/etat",consumes = MediaType.APPLICATION_JSON_VALUE)
  public Etat finByCode(){
        Etat etat = new Etat();
        etat = etatRep.findByCode("new");
            return etat;
 }

    @PutMapping (value="/encours")
    public Demande Encours (@Valid UUID uuid, BindingResult bindingResult) {
    Demande  dem = demandeRep.getOne(uuid);
        Etat etat = dem.getEtat();
            etat.setDesignation("Encours");
            dem.setEtat(etat);
            demandeRep.save(dem);
        return dem;

    }

}

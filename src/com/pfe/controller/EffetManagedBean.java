package com.pfe.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.pfe.model.ContratCpt;
import com.pfe.model.DetailEffet;
import com.pfe.model.DetailOperMoyPaiement;
import com.pfe.model.Extrait;
import com.pfe.model.GlobalRemise;
import com.pfe.model.MotifRejet;
import com.pfe.model.Operation;
import com.pfe.model.OperationMoyPay;
import com.pfe.security.LoginManagedBean;
import com.pfe.service.EffetService;
import com.pfe.service.OperationEffetService;
import com.pfe.service.OperationService;
import com.pfe.service.PersonnelService;
import com.pfe.service.RemiseService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean(name = "EffetCtrl")
@ViewScoped
public class EffetManagedBean implements Serializable {

	private static final long serialVersionUID = 4479407132444276797L;

	@ManagedProperty(value = "#{EffetService}")
	private EffetService service;

	@ManagedProperty(value = "#{RemiseService}")
	private RemiseService rService;
	@ManagedProperty(value = "#{PersonnelService}")
	private PersonnelService pService;
	@ManagedProperty(value = "#{OperationService}")
	private OperationService oService;
	@ManagedProperty(value = "#{OperationEffetService}")
	private OperationEffetService oEService;
	@ManagedProperty(value = "#{ContratCtrl}")
	private ContratManagedBean contratCtrl;
	@ManagedProperty(value = "#{LoginCtrl}")
	private LoginManagedBean loginCtrl;

	private JasperPrint jasperPrint;
	private LazyDataModel<DetailEffet> lazyModel;
	private List<DetailEffet> listEffets;
	private String etatListEffets;
	private int count;

	private DetailEffet effet;
	private DetailEffet selectedEffet;
	private GlobalRemise remise;
	private Operation operation;
	private String rib;
	private String nom;
	private Date dateE;
	private Date dateC;
	private String adr;
	private Long mont;
	private String num;
	private Long nbr;
	private Long montG;
	private Long numG;

	private String modeDeliv = "G";
	private Long montFrais = 0L;
	private String modeRech;
	private boolean numDisabled = false;
	private static String outcome = "";
	private String etatEffet = "";
	private Long selectedMotif;
	private List<MotifRejet> motifs;
	private Date selectedDate;

	@PostConstruct
	public void init() {
		lazyModel = new LazyEffetDataModel(service);
		operation = oService.findOperation(722L);
		motifs = service.findAllMotifs();
	}

	public void navigate() {
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(context, null, "/pages/secure/" + outcome + "?faces-redirect=true");
	}

	public String ajouterRemise() {
		boolean test;
		test = testerRemise();
		if (test == false)
			return "";
		GlobalRemise r = new GlobalRemise();
		r.setMontRem(montG);
		r.setNbrEffRem(nbr);
		r.setDatCreRem(LoginManagedBean.getActuelle());
		Serializable id = rService.createRemise(r);
		info("Félicitation !", "Prise en charge global remise ayant le numéro " + id.toString() + " avec succès.");
		return "/pages/secure/pecg.xhtml?faces-redirect=true";
	}

	public String ajouterEffet() {
		boolean test;
		test = testerEffet();
		if (contratCtrl.getC() == null) {
			test = false;
			error("Veuillez indiquer le compte client.");
		}
		if (remise == null) {
			test = false;
			error("Veuillez indiquer le numéro de remise global.");
		}
		if (verifierNumEffet(num, 41L) == false) {
			error("N° effet invalide.");
			test = false;
		} else {
			List<String> l = new ArrayList<String>();
			l.add("I");
			l.add("V");
			List<DetailEffet> listEffets = service.findEffetByCode(num, l);
			if (listEffets != null) {
				error("Effet dèja existant.");
				test = false;
			}
		}
		if (montG < mont) {
			error("Le montant de l'effet doit être inférieur au montant global.");
			test = false;
		}
		if (test == false)
			return "";

		DetailEffet e = new DetailEffet();
		e.setNumEff(num);
		e.setDatOpe(LoginManagedBean.getActuelle());
		e.setRibTir(rib);
		e.setNomTir(nom);
		e.setDatEch(dateE);
		e.setDatCre(dateC);
		e.setAdrTir(adr);
		e.setCodEtat("I");
		e.setCodVal(41L);
		e.setNumPres(1L);
		e.setCodEnrg(21L);
		e.setMntEff(mont);
		e.setcontratCpt(contratCtrl.getC());
		e.setGlobalRemise(remise);
		service.createEffet(e);

		info("Félicitation !", "Prise en charge remise encaissement avec succès.");
		return "/pages/secure/pecd.xhtml?faces-redirect=true";
	}

	public String modifierEffet() {
		boolean test;
		test = testerEffet();
		if (test == true)
			test = testerRemise();
		else
			testerRemise();
		if (montG < mont) {
			error("Le montant de l'effet doit être inférieur au montant global.");
			test = false;
		}
		if (test == false)
			return "";
		effet.setDatOpe(LoginManagedBean.getActuelle());
		effet.setRibTir(rib);
		effet.setNomTir(nom);
		effet.setDatEch(dateE);
		effet.setDatCre(dateC);
		effet.setAdrTir(adr);
		effet.setMntEff(mont);
		remise.setMontRem(montG);
		remise.setNbrEffRem(nbr);
		rService.updateRemise(remise);
		effet.setGlobalRemise(remise);
		service.updateEffet(effet);

		info("Félicitation !", "Modification remise encaissement avec succès.");
		return "/pages/secure/modif.xhtml?faces-redirect=true";
	}

	public String annulerEffet() {
		effet.setCodEtat("A");
		effet.setDatOpe(LoginManagedBean.getActuelle());
		service.updateEffet(effet);

		info("Félicitation !", "Annulation remise encaissement avec succès.");
		return "/pages/secure/annul.xhtml?faces-redirect=true";
	}

	public String validerEffet() {
		effet.setCodEtat("V");
		effet.setDatOpe(LoginManagedBean.getActuelle());
		OperationMoyPay oMP = new OperationMoyPay();
		oMP.setOperation(operation);
		oMP.setCodEtatOmp("V");
		oMP.setCodSensOmp("C");
		oMP.setDatOperOmp(LoginManagedBean.getActuelle());
		oMP.setNumPcedOmp(effet.getcontratCpt().getPersonne().getNumPcePers());
		oMP.setContratCpt(effet.getcontratCpt());
		oMP.setDevise(effet.getcontratCpt().getDevise());
		oMP.setTypePieceDemandeur(effet.getcontratCpt().getPersonne().getTypePiece());
		oMP.setPersonnelInitiateur(pService.findPersonnel(loginCtrl.getUserName()));
		oMP.setCodRefCfOmp(num);
		oMP.setLibMotfOmp("Validation Effet");
		oMP.setMontDinOmp(effet.getMntEff());
		oMP.setMontSoldCcpt(effet.getContratCpt().getMontSoldCcpt());
		oMP.setMontApreOmp(effet.getContratCpt().getMontSoldCcpt() - 3L);
		DetailOperMoyPaiement dOMP = new DetailOperMoyPaiement();
		dOMP.setOperationMoyPay(oMP);
		dOMP.setCodTypDomp("C");
		dOMP.setLibCommission("commission remise effet");
		dOMP.setMontValDomp(3L);
		oEService.updateOperationEffet(effet, oMP, dOMP);
		ContratCpt c = effet.getContratCpt();
		c.setMontSoldCcpt(oMP.getMontApreOmp());
		contratCtrl.getService().updateContrat(c);
		info("Félicitation !", "Validation remise encaissement avec succès.");
		return "/pages/secure/valid.xhtml?faces-redirect=true";
	}
	
	public String sortEffet() {
		String msg="";
		if (etatEffet.equals("R")) {
			selectedEffet.setCodEtat(etatEffet);
			selectedEffet.setDatOpe(LoginManagedBean.getActuelle());
			selectedEffet.setMotifRejet(service.findMotifByCode(selectedMotif));
			Operation operation = oService.findOperation(827L);
			OperationMoyPay oMP = new OperationMoyPay();
			oMP.setOperation(operation);
			oMP.setCodEtatOmp("R");
			oMP.setCodSensOmp("D");
			oMP.setDatOperOmp(LoginManagedBean.getActuelle());
			oMP.setContratCpt(selectedEffet.getcontratCpt());
			oMP.setPersonnelInitiateur(pService.findPersonnel(loginCtrl.getUserName()));
			oMP.setCodRefCfOmp(num);
			oMP.setLibMotfOmp("Réception sort Effet");
			oMP.setMontSoldCcpt(selectedEffet.getContratCpt().getMontSoldCcpt());
			oMP.setMontApreOmp(selectedEffet.getContratCpt().getMontSoldCcpt());
			oEService.updateOperationEffet(selectedEffet, oMP);
			msg = "Rejet effet avec succès.";
		}
		else if (etatEffet.equals("P"))
		{
			selectedEffet.setCodEtat(etatEffet);
			selectedEffet.setDatOpe(LoginManagedBean.getActuelle());
			Operation operation = oService.findOperation(826L);
			OperationMoyPay oMP = new OperationMoyPay();
			oMP.setOperation(operation);
			oMP.setCodEtatOmp("P");
			oMP.setCodSensOmp("C");
			oMP.setDatOperOmp(LoginManagedBean.getActuelle());
			oMP.setContratCpt(selectedEffet.getcontratCpt());
			oMP.setPersonnelInitiateur(pService.findPersonnel(loginCtrl.getUserName()));
			oMP.setCodRefCfOmp(num);
			oMP.setLibMotfOmp("Réception sort Effet");
			oMP.setMontSoldCcpt(selectedEffet.getContratCpt().getMontSoldCcpt());
			oMP.setMontApreOmp(selectedEffet.getContratCpt().getMontSoldCcpt() + selectedEffet.getMntEff());
			oEService.updateOperationEffet(selectedEffet, oMP);
			ContratCpt c = selectedEffet.getContratCpt();
			c.setMontSoldCcpt(oMP.getMontApreOmp());
			contratCtrl.getService().updateContrat(c);
			msg = "Paiement effet avec succès.";
		}
		info("Information", msg);
		return "/pages/secure/sort.xhtml?faces-redirect=true";
	}

	public String editerPapillon() {
		Operation operation = oService.findOperation(950L);
		selectedEffet.setCodEtat("E");
		selectedEffet.setDatOpe(LoginManagedBean.getActuelle());
		OperationMoyPay oMP = new OperationMoyPay();
		oMP.setOperation(operation);
		oMP.setCodEtatOmp("E");
		oMP.setCodSensOmp("D");
		oMP.setDatOperOmp(LoginManagedBean.getActuelle());
		oMP.setContratCpt(selectedEffet.getcontratCpt());
		oMP.setPersonnelInitiateur(pService.findPersonnel(loginCtrl.getUserName()));
		oMP.setCodRefCfOmp(num);
		oMP.setLibMotfOmp("Edition Papillon Effet");
		oMP.setMontSoldCcpt(selectedEffet.getContratCpt().getMontSoldCcpt());
		oMP.setMontApreOmp(selectedEffet.getContratCpt().getMontSoldCcpt());
		oEService.updateOperationEffet(selectedEffet, oMP);
		info("Félicitation !", "Edition papillon effet avec succès.");
		return "/pages/secure/editpap.xhtml?faces-redirect=true";
	}

	public String delivrerEffet() {
		Operation operation = oService.findOperation(961L);
		selectedEffet.setCodEtat("D");
		selectedEffet.setDatOpe(LoginManagedBean.getActuelle());
		OperationMoyPay oMP = new OperationMoyPay();
		oMP.setOperation(operation);
		oMP.setCodEtatOmp("D");
		oMP.setCodSensOmp("D");
		oMP.setDatOperOmp(LoginManagedBean.getActuelle());
		oMP.setContratCpt(selectedEffet.getcontratCpt());
		oMP.setPersonnelInitiateur(pService.findPersonnel(loginCtrl.getUserName()));
		oMP.setCodRefCfOmp(num);
		oMP.setLibMotfOmp("Délivrance Effet");
		oMP.setMontSoldCcpt(selectedEffet.getContratCpt().getMontSoldCcpt());
		if (modeDeliv.equals("C")) {
			oMP.setMontApreOmp(selectedEffet.getContratCpt().getMontSoldCcpt() - montFrais);
			DetailOperMoyPaiement dOMP = new DetailOperMoyPaiement();
			dOMP.setOperationMoyPay(oMP);
			dOMP.setCodTypDomp("C");
			dOMP.setLibCommission("commission délivrance effet");
			dOMP.setMontValDomp(montFrais);
			oEService.updateOperationEffet(selectedEffet, oMP, dOMP);
		} else if (modeDeliv.equals("G")){
			oMP.setMontApreOmp(selectedEffet.getContratCpt().getMontSoldCcpt());
			oEService.updateOperationEffet(selectedEffet, oMP);
		}
		ContratCpt c = selectedEffet.getContratCpt();
		c.setMontSoldCcpt(oMP.getMontApreOmp());
		contratCtrl.getService().updateContrat(c);
		info("Félicitation !", "Délivrance effet avec succès.");
		return "/pages/secure/deliv.xhtml?faces-redirect=true";

	}

	public void chercherRemise() {
		remise = rService.findRemise(numG);
		if (remise == null) {
			error("Remise inexistante.");
		} else {
			this.setNumG(remise.getNumRem());
			this.setMontG(remise.getMontRem());
			this.setNbr(remise.getNbrEffRem());
			numDisabled = true;
			info("Information", "Remise trouvée.");
		}

	}

	public void chercherEffet() {
		if (verifierNumEffet(num, 41L) == false) {
			error("N° effet invalide.");
		} else {
			List<String> l = new ArrayList<String>();
			l.add("I");
			listEffets = service.findEffetByCode(num, l);
			if (listEffets == null) {
				error("Effet inexistant.");
			} else {
				effet = listEffets.get(0);
				remise = effet.getGlobalRemise();
				this.setAdr(effet.getAdrTir());
				this.setMont(effet.getMntEff());
				this.setDateC(effet.getDatCre());
				this.setDateE(effet.getDatEch());
				this.setNom(effet.getNomTir());
				this.setRib(effet.getRibTir());
				this.setRemise(effet.getGlobalRemise());
				this.setMontG(effet.getGlobalRemise().getMontRem());
				this.setNbr(effet.getGlobalRemise().getNbrEffRem());
				numDisabled = true;
				info("Information", "Effet trouvé.");
			}
		}

	}

	public void selectEffet() {
		List<String> l = new ArrayList<String>();
		l.add(selectedEffet.getCodEtat());
		effet = service.findEffetByCode(selectedEffet.getNumEff(), l).get(0);
		this.setAdr(selectedEffet.getAdrTir());
		this.setMont(selectedEffet.getMntEff());
		this.setDateC(selectedEffet.getDatCre());
		this.setDateE(selectedEffet.getDatEch());
		this.setNom(selectedEffet.getNomTir());
		this.setRib(selectedEffet.getRibTir());
		this.setRemise(selectedEffet.getGlobalRemise());
		this.setMontG(selectedEffet.getGlobalRemise().getMontRem());
		this.setNbr(selectedEffet.getGlobalRemise().getNbrEffRem());
		info("Information", "Effet selectionné.");
	}

	public void afficheParCpt() {
		lazyModel = new LazyEffetDataModel(service, contratCtrl.getC());
	}

	public Void generatePDF(String report) throws JRException, IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext servletContext = (ServletContext) externalContext.getContext();
		String reportPath = servletContext.getRealPath("/reports/" + report + "-APP.jasper");
		Map<String, Object> parameters = new HashMap<String, Object>();
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(new ArrayList<>());
		if (report.equals("Liste")) {
			if (etatListEffets != null) {
				List<String> l = new ArrayList<String>();
				l.add(etatListEffets);
				listEffets = service.findAllEffetsByCode(l);
			}
			beanCollectionDataSource = new JRBeanCollectionDataSource(listEffets);
			jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);
		} else if (report.equals("Extrait")) {
			ContratCpt c = contratCtrl.getC();
			if (c != null) {
				List<OperationMoyPay> list1 = oService.findAllOperationMoyPays(c);
				List<Extrait> l = new ArrayList<Extrait>();
				for (int i = 0; i < list1.size(); i++) {
					OperationMoyPay oMP = list1.get(i);
					Extrait e1 = new Extrait(oMP.getDatOperOmp(), oMP.getOperation().getLibOperOper(),
							oMP.getMontDinOmp(), oMP.getMontApreOmp());
					Long code = oMP.getOperation().getCodOperOper();
					if (code == 722L || code == 826L)
						e1.setCred("X");
					l.add(e1);
					Set<DetailOperMoyPaiement> list2 = oMP.getDetailOperMoyPaiements();
					for (Iterator<DetailOperMoyPaiement> it = list2.iterator(); it.hasNext();) {
						DetailOperMoyPaiement dOMP = it.next();
						Extrait e2 = new Extrait(oMP.getDatOperOmp(), dOMP.getLibCommission(), dOMP.getMontValDomp(),
								null);
						e2.setDeb("X");
						l.add(e2);
					}
				}
				parameters.put("etat", c.getCodEtatCcpt());
				parameters.put("devise", c.getDevise().getLibDevDev());
				parameters.put("codPrd", c.getContratCptId().getCodPrdPrd());
				parameters.put("codStrc", c.getContratCptId().getCodStrcStrc());
				parameters.put("numCcpt", c.getContratCptId().getNumCcptCcpt());
				parameters.put("montSoldCcpt", c.getMontSoldCcpt());
				beanCollectionDataSource = new JRBeanCollectionDataSource(l);
				System.out.println(l);
				jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);
			}
			else
			{
				error("Vous devez indiquer un compte client valide.");
				return null;
			}
		} else if (report.equals("Papillon")) {
			if (selectedEffet != null)
			{
				parameters.put("P_BQ_DES", "03");
				parameters.put("P_AG_DES", selectedEffet.getContratCpt().getContratCptId().getCodStrcStrc());
				parameters.put("P_BQ_TIR", selectedEffet.getRibTir().substring(0, 2));
				parameters.put("P_AG_TIR", selectedEffet.getRibTir().substring(2, 5));
				parameters.put("P_NOM_BEN", selectedEffet.getcontratCpt().getContratCptId().getCompteClient());
				parameters.put("P_NUM_EFF", selectedEffet.getNumEff());
				parameters.put("P_DAT_PRES", selectedEffet.getDatCre());
				parameters.put("P_RIB_TIR", selectedEffet.getRibTir());
				parameters.put("P_MOTIF", selectedEffet.getMotifRejet().getMotifRej());
				parameters.put("P_MNT", selectedEffet.getMntEff());
				jasperPrint = JasperFillManager.fillReport(reportPath, parameters, new JREmptyDataSource());
			}
		} else if (report.equals("Bordereau")) {
			if (selectedDate != null)
			{
				parameters.put("date", selectedDate);
				List<String> l = new ArrayList<String>();
				l.add("V");
				listEffets = service.findAllEffetsByCodeDate(l, selectedDate);
				beanCollectionDataSource = new JRBeanCollectionDataSource(listEffets);
				jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);
			}
			else
			{
				error("Vous devez indiquer la date.");
				return null;
			}
		}
		
		
		try {
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + report + ".pdf");
			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			httpServletResponse.setContentLength(bytes.length);
			httpServletResponse.getOutputStream().write(bytes, 0, bytes.length);
			httpServletResponse.getOutputStream().flush();
			httpServletResponse.getOutputStream().close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			error("Désolé, une erreur s'est produite lors de la génération de PDF.");
		}
		return null;
	}

	public boolean testerRemise() {
		boolean test = true;
		if (montG.toString().length() > 15 || montG <= 0) {
			error("Le montant doit être supérieur strictement à 0 et inférieur à 999999999999999.");
			test = false;
		}
		if (nbr > 10) {
			error("Le nombre d'effets doit être inférieur à 10");
			test = false;
		}
		return test;
	}

	public boolean testerEffet() {
		boolean test = true;
		try {
			String cle = calculerCleRIB(rib.substring(0, 18));
			if (!cle.equals(rib.substring(18, 20))) {
				error("RIB invalide.");
				test = false;
			}
		} catch (Exception e) {
			error("RIB invalide.");
			test = false;
		}
		if (dateC.after(dateE)) {
			error("La date de creation doit être inférieur à la date d'échéance.");
			test = false;
		}
		if (mont.toString().length() > 15 || mont <= 0) {
			error("Le montant doit être supérieur strictement à 0 et inférieur à 999999999999999.");
			test = false;
		}
		return test;
	}

	public static boolean verifierNumEffet(String numEffet, Long codVal) {
		if (codVal.equals(41L)) {
			try {
				String num = numEffet.substring(0, 10);
				String cle = numEffet.substring(10, 12);
				if (calculerCleEffet(num).equals(cle)) {
					String numSerie = num.substring(0, 3);
					if (Long.valueOf(numSerie).longValue() > 0 && Long.valueOf(cle).longValue() > 0)
						return true;
					else
						return false;
				} else {
					return false;
				}

			} catch (Exception ex) {
				return false;
			}
		} else
			return false;
	}

	public void verifierRibBna() {
		try {
			String cle = calculerCleRIB(rib.substring(0, 18));
			System.out.println(cle);
			if (!cle.equals(rib.substring(18, 20))) {
				error("RIB invalide.");
				return;
			}
		} catch (Exception e) {
			error("RIB invalide.");
			return;
		}
		try {
			if (rib.substring(0, 2).equals("03")) {
				String ribSuffixe = rib.substring(5);
				Long codStrc = Long.valueOf(ribSuffixe.toString().substring(0, 3));
				Long codPrd = Long.valueOf(ribSuffixe.toString().substring(3, 7));
				Long numCpt = Long.valueOf(ribSuffixe.toString().substring(7, 13));
				ContratCpt c = contratCtrl.getService().findContratWithPers(codStrc, codPrd, numCpt);
				if (c != null) {
					nom = c.getPersonne().getNomNomPers();
					adr = c.getPersonne().getAdresseResid().toString();
					info("Information", "Le RIB tiré est un compte BNA.");
				}
			}
		} catch (Exception e) {
			error("RIB invalide.");
		}
	}

	public static String calculerCleEffet(String numEffet) {
		String resultat = "";
		if (numEffet.length() == 10) {
			String RI = numEffet;
			BigInteger rr = new BigInteger(RI.concat("00"));
			int rest = rr.mod(new BigInteger("97")).intValue();
			int nb = 97 - rest;
			String nbr = "" + nb;
			if (nbr.length() == 1)
				resultat = "0" + nbr;
			else
				resultat = nbr;
		}
		return resultat;
	}

	public static String calculerCleRIB(String RIB) {
		String resultat = "";
		if (RIB.length() == 18) {
			String RI = RIB;
			BigInteger rr = new BigInteger(RI.concat("00"));
			int rest = rr.mod(new BigInteger("97")).intValue();
			int nb = 97 - rest;
			String nbr = "" + nb;
			if (nbr.length() == 1)
				resultat = "0" + nbr;
			else
				resultat = nbr;
		}
		return resultat;
	}

	public void info(String title, String message) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));

	}

	public void error(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention !", message));
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Effet Selected", ((DetailEffet) event.getObject()).getNumSeq().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public EffetService getService() {
		return service;
	}

	public void setService(EffetService service) {
		this.service = service;
	}

	public RemiseService getrService() {
		return rService;
	}

	public void setrService(RemiseService rService) {
		this.rService = rService;
	}

	public PersonnelService getpService() {
		return pService;
	}

	public void setpService(PersonnelService pService) {
		this.pService = pService;
	}

	public OperationService getoService() {
		return oService;
	}

	public void setoService(OperationService oService) {
		this.oService = oService;
	}

	public OperationEffetService getoEService() {
		return oEService;
	}

	public void setoEService(OperationEffetService oEService) {
		this.oEService = oEService;
	}

	public ContratManagedBean getContratCtrl() {
		return contratCtrl;
	}

	public void setContratCtrl(ContratManagedBean contratCtrl) {
		this.contratCtrl = contratCtrl;
	}

	public LoginManagedBean getLoginCtrl() {
		return loginCtrl;
	}

	public void setLoginCtrl(LoginManagedBean loginCtrl) {
		this.loginCtrl = loginCtrl;
	}

	public LazyDataModel<DetailEffet> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<DetailEffet> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public List<DetailEffet> getListEffets() {
		return listEffets;
	}

	public void setListEffets(List<DetailEffet> listEffets) {
		this.listEffets = listEffets;
	}

	public String getEtatListEffets() {
		return etatListEffets;
	}

	public void setEtatListEffets(String etatListEffets) {
		this.etatListEffets = etatListEffets;
	}

	public int getCount() {
		count = listEffets.size();
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public DetailEffet getEffet() {
		return effet;
	}

	public void setEffet(DetailEffet effet) {
		this.effet = effet;
	}

	public DetailEffet getSelectedEffet() {
		return selectedEffet;
	}

	public void setSelectedEffet(DetailEffet selectedEffet) {
		this.selectedEffet = selectedEffet;
	}

	public GlobalRemise getRemise() {
		return remise;
	}

	public void setRemise(GlobalRemise remise) {
		this.remise = remise;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateE() {
		return dateE;
	}

	public void setDateE(Date dateE) {
		this.dateE = dateE;
	}

	public Date getDateC() {
		return dateC;
	}

	public void setDateC(Date dateC) {
		this.dateC = dateC;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public Long getMont() {
		return mont;
	}

	public void setMont(Long mont) {
		this.mont = mont;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Long getNbr() {
		return nbr;
	}

	public void setNbr(Long nbr) {
		this.nbr = nbr;
	}

	public Long getMontG() {
		return montG;
	}

	public void setMontG(Long montG) {
		this.montG = montG;
	}

	public Long getNumG() {
		return numG;
	}

	public void setNumG(Long numG) {
		this.numG = numG;
	}

	public String getModeDeliv() {
		return modeDeliv;
	}

	public void setModeDeliv(String modeDeliv) {
		this.modeDeliv = modeDeliv;
	}

	public Long getMontFrais() {
		return montFrais;
	}

	public void setMontFrais(Long montFrais) {
		this.montFrais = montFrais;
	}

	public String getModeRech() {
		return modeRech;
	}

	public void setModeRech(String modeRech) {
		this.modeRech = modeRech;
	}

	public boolean isNumDisabled() {
		return numDisabled;
	}

	public void setNumDisabled(boolean numDisabled) {
		this.numDisabled = numDisabled;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		EffetManagedBean.outcome = outcome;
	}

	public String getEtatEffet() {
		return etatEffet;
	}

	public void setEtatEffet(String etatEffet) {
		this.etatEffet = etatEffet;
	}

	public Long getSelectedMotif() {
		return selectedMotif;
	}

	public void setSelectedMotif(Long selectedMotif) {
		this.selectedMotif = selectedMotif;
	}

	public List<MotifRejet> getMotifs() {
		return motifs;
	}

	public void setMotifs(List<MotifRejet> motifs) {
		this.motifs = motifs;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

}

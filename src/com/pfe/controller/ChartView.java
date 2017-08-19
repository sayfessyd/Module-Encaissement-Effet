package com.pfe.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.pfe.service.EffetService;
import com.pfe.service.OperationService;

@ManagedBean
@ViewScoped
public class ChartView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{EffetService}")
	private EffetService eService;
	@ManagedProperty(value = "#{OperationService}")
	private OperationService oService;

	private PieChartModel pieEffet;
	private PieChartModel pieOperation;

	private MeterGaugeChartModel meterGaugeOperation;

	private CartesianChartModel combinedModel1;
	private CartesianChartModel combinedModel2;

	private DonutChartModel donutModel1;

	@PostConstruct
	public void init() {
		createPieModels();
		createMeterGaugeModels();
		createCombinedModels();
		createDonutModels();
	}

	public void createPieModels() {
		createPieEffet();
		createPieOperation();
	}

	public void createPieEffet() {
		pieEffet = new PieChartModel();

		pieEffet.set("Initialisés", 540);
		pieEffet.set("Annulés", 325);
		pieEffet.set("Validés", 702);

		pieEffet.setTitle("Effets par état");
		pieEffet.setLegendPosition("w");
		pieEffet.setShowDataLabels(true);
		pieEffet.setSeriesColors("55A616,FFD538,DFD9C3");

	}

	public void createPieOperation() {
		pieOperation = new PieChartModel();

		pieOperation.set("Edition", 540);
		pieOperation.set("Délivrance", 325);
		pieOperation.set("Remise", 702);

		pieOperation.setTitle("Operations par type");
		pieOperation.setLegendPosition("e");
		pieOperation.setFill(false);
		pieOperation.setShowDataLabels(true);
		pieOperation.setDiameter(150);
		pieOperation.setSeriesColors("55A616,FFD538,DFD9C3");
	}

	public MeterGaugeChartModel initMeterGaugeModel() {
		List<Number> intervals = new ArrayList<Number>() {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			{
				add(220);
			}
		};

		return new MeterGaugeChartModel(140, intervals);
	}

	public void createMeterGaugeModels() {
		meterGaugeOperation = initMeterGaugeModel();
		meterGaugeOperation.setTitle("Operations/Journée");
		meterGaugeOperation.setGaugeLabel("Op/J");
		meterGaugeOperation.setSeriesColors("FFFDF6");
	}

	public void createCombinedModels() {
		combinedModel1 = new BarChartModel();

		LineChartSeries boys = new LineChartSeries();
		boys.setLabel("Paiement");

		boys.set("J-4", 120);
		boys.set("J-3", 100);
		boys.set("J-2", 44);
		boys.set("J-1", 150);
		boys.set("J", 25);

		LineChartSeries girls = new LineChartSeries();
		girls.setLabel("Rejet");

		girls.set("J-4", 52);
		girls.set("J-3", 60);
		girls.set("J-2", 110);
		girls.set("J-1", 135);
		girls.set("J", 120);

		combinedModel1.addSeries(boys);
		combinedModel1.addSeries(girls);
		combinedModel1.setSeriesColors("55A616,FFD538");
		combinedModel1.setTitle("Operations Paiement/Rejet");
		combinedModel1.setLegendPosition("ne");
		combinedModel1.setMouseoverHighlight(false);
		combinedModel1.setShowDatatip(false);
		combinedModel1.setShowPointLabels(true);
		combinedModel1.setAnimate(true);
		Axis yAxis = combinedModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(200);

		combinedModel2 = new BarChartModel();

		BarChartSeries boys2 = new BarChartSeries();
		boys2.setLabel("Validés");

		boys2.set("J-4", 120);
		boys2.set("J-3", 100);
		boys2.set("J-2", 44);
		boys2.set("J-1", 150);
		boys2.set("J", 25);

		LineChartSeries girls2 = new LineChartSeries();
		girls2.setLabel("Compensés");

		girls2.set("J-4", 52);
		girls2.set("J-3", 60);
		girls2.set("J-2", 110);
		girls2.set("J-1", 135);
		girls2.set("J", 120);

		combinedModel2.addSeries(boys2);
		combinedModel2.addSeries(girls2);
		combinedModel2.setSeriesColors("55A616,FFD538");
		combinedModel2.setTitle("Effets Validés/Compensés ");
		combinedModel2.setLegendPosition("ne");
		combinedModel2.setMouseoverHighlight(false);
		combinedModel2.setShowDatatip(false);
		combinedModel2.setShowPointLabels(true);
		combinedModel2.setAnimate(true);
		Axis yAxis2 = combinedModel2.getAxis(AxisType.Y);
		yAxis2.setMin(0);
		yAxis2.setMax(200);
	}

	public void createDonutModels() {
		donutModel1 = initDonutModel();
		donutModel1.setTitle("Comptes Produit/Devise");
		donutModel1.setLegendPosition("w");
		donutModel1.setSeriesColors("55A616,FFD538,DFD9C3");
		donutModel1.setShowDataLabels(true);
	}

	public DonutChartModel initDonutModel() {
		DonutChartModel model = new DonutChartModel();

		Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
		circle1.put("103", 150);
		circle1.put("115", 400);
		circle1.put("101", 200);
		model.addCircle(circle1);

		Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
		circle2.put("103", 540);
		circle2.put("115", 125);
		circle2.put("101", 702);
		model.addCircle(circle2);

		Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
		circle3.put("103", 40);
		circle3.put("115", 325);
		circle3.put("101", 402);
		model.addCircle(circle3);

		return model;
	}

	public EffetService geteService() {
		return eService;
	}

	public void seteService(EffetService eService) {
		this.eService = eService;
	}

	public OperationService getoService() {
		return oService;
	}

	public void setoService(OperationService oService) {
		this.oService = oService;
	}

	public PieChartModel getPieEffet() {
		return pieEffet;
	}

	public PieChartModel getPieOperation() {
		return pieOperation;
	}

	public MeterGaugeChartModel getMeterGaugeOperation() {
		return meterGaugeOperation;
	}

	public CartesianChartModel getCombinedModel1() {
		return combinedModel1;
	}

	public CartesianChartModel getCombinedModel2() {
		return combinedModel2;
	}

	public DonutChartModel getDonutModel1() {
		return donutModel1;
	}

}
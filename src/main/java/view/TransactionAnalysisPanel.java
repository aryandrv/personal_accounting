package view;

import controller.TransactionController;
import enums.TypeEnum;
import model.entity.TitleSummary;
import model.entity.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TransactionAnalysisPanel extends JPanel {

    private JLabel fromDateLabel;
    private JLabel toDateLabel;

    private JXDatePicker fromDatePicker;
    private JXDatePicker toDatePicker;

    private JButton analyzeButton;

    private JPanel chartsContainer;  // پنلی که هر دو چارت رو نگه می‌داره
    private JPanel titleAnalysisPanel;

    private User user;
    private DefaultTableModel titleTableModel;
    private JTable titleSummaryTable;
    private JScrollPane titleTableScrollPane;
    private ChartPanel pieChartIncomePanel;
    private ChartPanel pieChartCostPanel;
    private ChartPanel barChartIncomePanel;
    private ChartPanel barChartCostPanel;

    public TransactionAnalysisPanel() {
        initComponent();
        drawGUI();
    }

    private void initComponent() {

        fromDateLabel = new JLabel("From Date:");
        toDateLabel = new JLabel("To Date:");

        fromDatePicker = new JXDatePicker();
        toDatePicker = new JXDatePicker();
        fromDatePicker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        toDatePicker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));

        analyzeButton = new JButton("Analyze");
        analyzeButton.addActionListener(e -> fillForm(user));

        chartsContainer = new JPanel();
        chartsContainer.setLayout(new BoxLayout(chartsContainer, BoxLayout.Y_AXIS));

        titleAnalysisPanel = new JPanel();
        titleAnalysisPanel.setLayout(new BorderLayout());

        // جدول
        titleTableModel = new DefaultTableModel(new String[]{"Title", "Type", "Amount", "Transaction Count"}, 0);
        titleSummaryTable = new JTable(titleTableModel);
        titleTableScrollPane = new JScrollPane(titleSummaryTable);

        // پنل نمودارهای Titles دو در دو کنار هم
        JPanel chartsGridPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        pieChartIncomePanel = new ChartPanel(null);
        pieChartCostPanel = new ChartPanel(null);
        barChartIncomePanel = new ChartPanel(null);
        barChartCostPanel = new ChartPanel(null);

        chartsGridPanel.add(pieChartIncomePanel);
        chartsGridPanel.add(pieChartCostPanel);
        chartsGridPanel.add(barChartIncomePanel);
        chartsGridPanel.add(barChartCostPanel);

        titleAnalysisPanel.add(chartsGridPanel, BorderLayout.CENTER);
        titleAnalysisPanel.add(titleTableScrollPane, BorderLayout.SOUTH);

        chartsContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        chartsContainer.add(titleAnalysisPanel);
    }

    private void drawGUI() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGap(15)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(fromDateLabel)
                                        .addGap(5)
                                        .addComponent(fromDatePicker, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addGap(30)
                                        .addComponent(toDateLabel)
                                        .addGap(5)
                                        .addComponent(toDatePicker, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addGap(30)
                                        .addComponent(analyzeButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                )
                                .addComponent(chartsContainer, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(15)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(fromDateLabel)
                                .addComponent(fromDatePicker, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addComponent(toDateLabel)
                                .addComponent(toDatePicker, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addComponent(analyzeButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(30)
                        .addComponent(chartsContainer, GroupLayout.PREFERRED_SIZE, 2000, GroupLayout.PREFERRED_SIZE)
                        .addGap(30)
        );
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void fillForm(User user) {
        this.user = user;

        Date fromDate = fromDatePicker.getDate();
        Date toDate = toDatePicker.getDate();

        if (fromDate == null || toDate == null) {
            JOptionPane.showMessageDialog(this, "Please select both dates!");
            return;
        }

        LocalDate fromLocalDate = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate toLocalDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (fromLocalDate.isAfter(toLocalDate)) {
            JOptionPane.showMessageDialog(this, "From date should be before To date!");
            return;
        }

        Map<String, Double> report = TransactionController.getController().getIncomeExpenseByDateRange(user.getId(), fromLocalDate, toLocalDate);

        chartsContainer.removeAll();

        ChartPanel barChartPanel = createBarChartPanel(report, fromLocalDate, toLocalDate);
        ChartPanel pieChartPanel = createPieChartPanel(report, fromLocalDate, toLocalDate);

        chartsContainer.add(barChartPanel);
        chartsContainer.add(Box.createRigidArea(new Dimension(0, 20))); // فاصله عمودی بین دو نمودار
        chartsContainer.add(pieChartPanel);

        chartsContainer.add(Box.createRigidArea(new Dimension(0, 20))); // فاصله با نمودارهای اصلی
        chartsContainer.add(titleAnalysisPanel);

        try {
            fillTitleSummaryByType(user, fromLocalDate, toLocalDate);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading title summaries by type");
        }

        chartsContainer.revalidate();
        chartsContainer.repaint();
    }

    private ChartPanel createBarChartPanel(Map<String, Double> report, LocalDate from, LocalDate to) {
        double income = report.getOrDefault("income", 0.0);
        double cost = Math.abs(report.getOrDefault("cost", 0.0));
        double balance = income - cost;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(income, "Income", from + " to " + to);
        dataset.addValue(cost, "Cost", from + " to " + to);
        dataset.addValue(balance, "Balance", from + " to " + to);

        JFreeChart barChart = ChartFactory.createBarChart(
                "Income vs Cost (Bar Chart)",
                "Category",
                "Amount",
                dataset
        );

        CategoryPlot plot = barChart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(34, 139, 34));
        renderer.setSeriesPaint(1, new Color(255, 0, 0));
        renderer.setSeriesPaint(2, new Color(255, 219, 0));

        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);

        return new ChartPanel(barChart);
    }

    private ChartPanel createPieChartPanel(Map<String, Double> report, LocalDate from, LocalDate to) {
        double income = report.getOrDefault("income", 0.0);
        double cost = Math.abs(report.getOrDefault("cost", 0.0));

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Income", income);
        dataset.setValue("Cost", cost);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Income vs Cost",
                dataset,
                true, true, false);

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("Income",  new Color(34, 139, 34));
        plot.setSectionPaint("Cost", new Color(255, 0, 0));

        return new ChartPanel(pieChart);
    }

    private void fillTitleSummaryByType(User user, LocalDate from, LocalDate to) throws Exception {
        List<TitleSummary> summaries = TransactionController.getController()
                .getTitleSummariesByType(user.getId(), from, to);

        titleTableModel.setRowCount(0);

        DefaultPieDataset pieIncomeDataset = new DefaultPieDataset();
        DefaultPieDataset pieCostDataset = new DefaultPieDataset();

        DefaultCategoryDataset barIncomeDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset barCostDataset = new DefaultCategoryDataset();

        for (TitleSummary ts : summaries) {
            titleTableModel.addRow(new Object[]{
                    ts.getTitleName(),
                    ts.getType(),
                    String.format("%.2f", ts.getTotalAmount()),
                    ts.getTransactionCount()
            });

            if (TypeEnum.INCOME.equals(ts.getType())) {
                pieIncomeDataset.setValue(ts.getTitleName(), ts.getTotalAmount());
                barIncomeDataset.addValue(ts.getTotalAmount(), TypeEnum.INCOME.toString(), ts.getTitleName());
            } else if (TypeEnum.COST.equals(ts.getType())) {
                pieCostDataset.setValue(ts.getTitleName(), ts.getTotalAmount());
                barCostDataset.addValue(ts.getTotalAmount(), TypeEnum.COST.toString(), ts.getTitleName());
            }
        }

        JFreeChart pieIncomeChart = ChartFactory.createPieChart("Income by Title", pieIncomeDataset, true, true, false);
        JFreeChart pieCostChart = ChartFactory.createPieChart("Cost by Title", pieCostDataset, true, true, false);
        JFreeChart barIncomeChart = ChartFactory.createBarChart("Income by Title", "Title", "Amount", barIncomeDataset);
        JFreeChart barCostChart = ChartFactory.createBarChart("Cost by Title", "Title", "Amount", barCostDataset);

        pieChartIncomePanel.setChart(pieIncomeChart);
        pieChartCostPanel.setChart(pieCostChart);
        barChartIncomePanel.setChart(barIncomeChart);
        barChartCostPanel.setChart(barCostChart);
    }
}

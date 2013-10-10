package javafxapplication12;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import jfxtras.labs.scene.control.Agenda;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.util.Callback;

/**
 * A Google Calendar alike control.
 * Because the name "Calendar" is already in use to denote a data type (as in CalendarPicker), the name Agenda is used for this control.
 *
 * @see jfxtras.labs.scene.control.Agenda
 */
public class AgendaSample1 extends Application {

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 600,600));

        GridPane lGridPane = new GridPane();
        lGridPane.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        root.getChildren().add(lGridPane);
        int lRowIdx = 0;

        {
	        final Agenda lAgenda = new Agenda();
                lAgenda.setPrefSize(600, 600);
                lAgenda.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
	        lGridPane.add(lAgenda, 0, lRowIdx, 2, 1);
	        lRowIdx++;

		// setup appointment groups
		final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new HashMap<String, Agenda.AppointmentGroup>();
		lAppointmentGroupMap.put("group0", new Agenda.AppointmentGroupImpl().withStyleClass("group0"));
		lAppointmentGroupMap.put("group1", new Agenda.AppointmentGroupImpl().withStyleClass("group1"));
		lAppointmentGroupMap.put("group2", new Agenda.AppointmentGroupImpl().withStyleClass("group2"));
		lAppointmentGroupMap.put("group3", new Agenda.AppointmentGroupImpl().withStyleClass("group3"));
		lAppointmentGroupMap.put("group4", new Agenda.AppointmentGroupImpl().withStyleClass("group4"));
		lAppointmentGroupMap.put("group5", new Agenda.AppointmentGroupImpl().withStyleClass("group5"));
		lAppointmentGroupMap.put("group6", new Agenda.AppointmentGroupImpl().withStyleClass("group6"));
		lAppointmentGroupMap.put("group7", new Agenda.AppointmentGroupImpl().withStyleClass("group7"));
		lAppointmentGroupMap.put("group8", new Agenda.AppointmentGroupImpl().withStyleClass("group8"));
		lAppointmentGroupMap.put("group9", new Agenda.AppointmentGroupImpl().withStyleClass("group9"));
		lAppointmentGroupMap.put("group10", new Agenda.AppointmentGroupImpl().withStyleClass("group10"));
		lAppointmentGroupMap.put("group11", new Agenda.AppointmentGroupImpl().withStyleClass("group11"));
		lAppointmentGroupMap.put("group12", new Agenda.AppointmentGroupImpl().withStyleClass("group12"));
		lAppointmentGroupMap.put("group13", new Agenda.AppointmentGroupImpl().withStyleClass("group13"));
		lAppointmentGroupMap.put("group14", new Agenda.AppointmentGroupImpl().withStyleClass("group14"));
		lAppointmentGroupMap.put("group15", new Agenda.AppointmentGroupImpl().withStyleClass("group15"));
		lAppointmentGroupMap.put("group16", new Agenda.AppointmentGroupImpl().withStyleClass("group16"));
		lAppointmentGroupMap.put("group17", new Agenda.AppointmentGroupImpl().withStyleClass("group17"));
		lAppointmentGroupMap.put("group18", new Agenda.AppointmentGroupImpl().withStyleClass("group18"));
		lAppointmentGroupMap.put("group19", new Agenda.AppointmentGroupImpl().withStyleClass("group19"));
		lAppointmentGroupMap.put("group0", new Agenda.AppointmentGroupImpl().withStyleClass("group20"));
		lAppointmentGroupMap.put("group1", new Agenda.AppointmentGroupImpl().withStyleClass("group21"));
		lAppointmentGroupMap.put("group2", new Agenda.AppointmentGroupImpl().withStyleClass("group22"));
		lAppointmentGroupMap.put("group3", new Agenda.AppointmentGroupImpl().withStyleClass("group23"));
		for (String lId : lAppointmentGroupMap.keySet())
		{
			Agenda.AppointmentGroup lAppointmentGroup = lAppointmentGroupMap.get(lId);
			lAppointmentGroup.setDescription(lId);
			lAgenda.appointmentGroups().add(lAppointmentGroup);
		}
                
		// accept new appointments
		lAgenda.createAppointmentCallbackProperty().set(new Callback<Agenda.CalendarRange, Agenda.Appointment>()
		{
                    public Agenda.Appointment call(Agenda.CalendarRange calendarRange)
                    {
                        return new Agenda.AppointmentImpl()
                        .withStartTime(calendarRange.getStartCalendar())
                        .withEndTime(calendarRange.getEndCalendar())
                        .withSummary("new")
                        .withDescription("new")
                        .withAppointmentGroup(lAppointmentGroupMap.get("group1"));
                    }
		});
		
		// initial set
		Calendar lFirstDayOfWeekCalendar = getFirstDayOfWeekCalendar(lAgenda.getLocale(), lAgenda.getDisplayedCalendar());
		int lYear = lFirstDayOfWeekCalendar.get(Calendar.YEAR);
		int lMonth = lFirstDayOfWeekCalendar.get(Calendar.MONTH);
		int lDay = lFirstDayOfWeekCalendar.get(Calendar.DATE);
		lAgenda.appointments().addAll(
			new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 8, 00))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 11, 30))
				.withSummary("A")
				.withDescription("A much longer test description")
				.withAppointmentGroup(lAppointmentGroupMap.get("group7"))
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 8, 30))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 10, 00))
				.withSummary("B")
				.withDescription("A description 2")
				.withAppointmentGroup(lAppointmentGroupMap.get("group8"))
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 8, 30))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 9, 30))
				.withSummary("C")
				.withDescription("A description 3")
				.withAppointmentGroup(lAppointmentGroupMap.get("group9"))
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 9, 00))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 13, 30))
				.withSummary("D")
				.withDescription("A description 4")
				.withAppointmentGroup(lAppointmentGroupMap.get("group7"))
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 10, 30))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 11, 00))
				.withSummary("E")
				.withDescription("A description 4")
				.withAppointmentGroup(lAppointmentGroupMap.get("group7"))
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 12, 30))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 13, 30))
				.withSummary("F")
				.withDescription("A description 4")
				.withAppointmentGroup(lAppointmentGroupMap.get("group7"))
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 13, 00))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 13, 30))
				.withSummary("H")
				.withDescription("A description 4")
				.withAppointmentGroup(lAppointmentGroupMap.get("group7"))
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 14, 00))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 14, 45))
				.withSummary("G")
				.withDescription("A description 4")
				.withAppointmentGroup(lAppointmentGroupMap.get("group7"))
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 15, 00))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 16, 00))
				.withSummary("I")
				.withDescription("A description 4")
				.withAppointmentGroup(lAppointmentGroupMap.get("group7"))
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay, 15, 30))
				.withEndTime(new GregorianCalendar(lYear, lMonth, lDay, 16, 00))
				.withSummary("J")
				.withDescription("A description 4")
				.withAppointmentGroup(lAppointmentGroupMap.get("group7"))
		// -----
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay))
				.withSummary("all day1")
				.withDescription("A description")
				.withAppointmentGroup(lAppointmentGroupMap.get("group7"))
				.withWholeDay(true)
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay))
				.withSummary("all day2")
				.withDescription("A description")
				.withAppointmentGroup(lAppointmentGroupMap.get("group8"))
				.withWholeDay(true)
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay))
				.withSummary("all day3")
				.withDescription("A description3")
				.withAppointmentGroup(lAppointmentGroupMap.get("group9"))
				.withWholeDay(true)
		, 	new Agenda.AppointmentImpl()
				.withStartTime(new GregorianCalendar(lYear, lMonth, lDay + 1))
				.withSummary("all day")
				.withDescription("A description3")
				.withAppointmentGroup(lAppointmentGroupMap.get("group3"))
				.withWholeDay(true)
		);
		final String lIpsum = "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus";
		// day spanner
		{
			Calendar lStart = (Calendar)lFirstDayOfWeekCalendar.clone();
			lStart.add(Calendar.SECOND, 5);
			lStart.add(Calendar.DATE, 1);
			Calendar lEnd = (Calendar)lStart.clone();
			lEnd.add(Calendar.DATE, 2);
			
			Agenda.Appointment lAppointment = new Agenda.AppointmentImpl()
			.withStartTime(lStart)
			.withEndTime(lEnd)
			.withSummary(lIpsum.substring(0, new Random().nextInt(50)))
			.withDescription(lIpsum.substring(0, 10 + new Random().nextInt(lIpsum.length() - 10)))
			.withAppointmentGroup(lAppointmentGroupMap.get("group" + (new Random().nextInt(3) + 1)));
			
			lAgenda.appointments().add(lAppointment);
		}
		

		// update range
		final AtomicBoolean lSkippedFirstRangeChange = new AtomicBoolean(false);		
		lAgenda.calendarRangeCallbackProperty().set(new Callback<Agenda.CalendarRange, Void>()
		{
			public Void call(Agenda.CalendarRange arg0)
			{
				// the first change should not be processed, because it is set above
				if (lSkippedFirstRangeChange.get() == false)
				{
					lSkippedFirstRangeChange.set(true);
					return null;
				}
				
				// add a whole bunch of random appointments
				for (int i = 0; i < 20; i++)
				{
					Calendar lFirstDayOfWeekCalendar = getFirstDayOfWeekCalendar(lAgenda.getLocale(), lAgenda.getDisplayedCalendar());
					
					Calendar lStart = (Calendar)lFirstDayOfWeekCalendar.clone();
					lStart.add(Calendar.DATE, new Random().nextInt(7));
					lStart.add(Calendar.HOUR_OF_DAY, new Random().nextInt(24));
					lStart.add(Calendar.MINUTE, new Random().nextInt(60));
					
					Calendar lEnd = (Calendar)lStart.clone();
					lEnd.add(Calendar.MINUTE, 15 + new Random().nextInt(24 * 60));
					
					Agenda.Appointment lAppointment = new Agenda.AppointmentImpl()
					.withStartTime(lStart)
					.withEndTime(lEnd)
					.withWholeDay(new Random().nextInt(50) > 40)
					.withSummary(lIpsum.substring(0, new Random().nextInt(50)))
					.withDescription(lIpsum.substring(0, new Random().nextInt(lIpsum.length())))
					.withAppointmentGroup(lAppointmentGroupMap.get("group" + (new Random().nextInt(24))));					
					lAgenda.appointments().add(lAppointment);
				}
				return null;
			}
		});
                
//	        ComboBox<String> lComboBox = new ComboBox<String>(FXCollections.observableArrayList("Single", "Range", "Multiple"));
//	        lComboBox.valueProperty().addListener(new ChangeListener<String>()
//                {
//                    @Override
//                    public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)
//                    {
//                            if (newValue.startsWith("S")) lAgenda.setMode(Agenda.Mode.SINGLE);
//                            if (newValue.startsWith("R")) lAgenda.setMode(Agenda.Mode.RANGE);
//                            if (newValue.startsWith("M")) lAgenda.setMode(Agenda.Mode.MULTIPLE);
//                    }
//                });
//	        lComboBox.setValue("Single");
//	        lComboBox.setPrefWidth(200); 
//	        lGridPane.add(new Label("Mode:"), 0, lRowIdx);
//	        lGridPane.add(lComboBox, 1, lRowIdx);
//	        lRowIdx++;
        }
    }

	
    /**
     * get the calendar for the first day of the week
     */
    static private Calendar getFirstDayOfWeekCalendar(Locale locale, Calendar c)
    {
        // result
        int lFirstDayOfWeek = Calendar.getInstance(locale).getFirstDayOfWeek();
        int lCurrentDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int lDelta = 0;
        if (lFirstDayOfWeek <= lCurrentDayOfWeek)
        {
                lDelta = -lCurrentDayOfWeek + lFirstDayOfWeek;
        }
        else
        {
                lDelta = -lCurrentDayOfWeek - (7-lFirstDayOfWeek);
        }
        c = ((Calendar)c.clone());
        c.add(Calendar.DATE, lDelta);
        return c;
    }

    public double getSampleWidth() { return 600; }

    public double getSampleHeight() { return 600; }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}

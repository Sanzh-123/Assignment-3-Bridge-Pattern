import domain.*;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && "--demo".equals(args[0])) {
            runDemo();
        } else {
            System.out.println("Use --demo flag to run required checks.");
        }
    }

    private static void runDemo() {
        int passCount = 0;

        // T1: A1 (Reminder) with I1 (EmailChannel)
        Reminder reminder1 = new Reminder("N-101", "Meeting at 10 AM", new EmailChannel());
        String resT1 = reminder1.execute();
        boolean passT1 = "EMAIL [Header: Notification] Message: Meeting at 10 AM".equals(resT1);
        printCheck("T1", passT1, "Reminder + EmailChannel", resT1, "EMAIL [Header: Notification] Message: Meeting at 10 AM");
        if (passT1) passCount++;

        // T2: A1 (Reminder) with I2 (SmsChannel)
        Reminder reminder2 = new Reminder("N-101", "Meeting at 10 AM", new SmsChannel());
        String resT2 = reminder2.execute();
        boolean passT2 = "SMS: Meeting at 10 AM".equals(resT2);
        printCheck("T2", passT2, "Reminder + SmsChannel", resT2, "SMS: Meeting at 10 AM");
        if (passT2) passCount++;

        // T3: A2 (UrgentAlert) with I1 (EmailChannel)
        UrgentAlert alert1 = new UrgentAlert("N-202", "Server Down", new EmailChannel());
        String resT3 = alert1.execute();
        boolean passT3 = "EMAIL [Header: Notification] Message: URGENT: Server Down".equals(resT3);
        printCheck("T3", passT3, "UrgentAlert + EmailChannel", resT3, "EMAIL [Header: Notification] Message: URGENT: Server Down");
        if (passT3) passCount++;

        // T4: A2 (UrgentAlert) with I2 (SmsChannel)
        UrgentAlert alert2 = new UrgentAlert("N-202", "Server Down", new SmsChannel());
        String resT4 = alert2.execute();
        boolean passT4 = "SMS: URGENT: Server Down".equals(resT4);
        printCheck("T4", passT4, "UrgentAlert + SmsChannel", resT4, "SMS: URGENT: Server Down");
        if (passT4) passCount++;

        // T5: Runtime Switch on Same Object
        Reminder origReminder = new Reminder("N-999", "Submit Assignment", new EmailChannel());
        Reminder sameRef = origReminder; // Retain reference
        String beforeSwitch = origReminder.execute();
        
        origReminder.setImplementation(new SmsChannel());
        String afterSwitch = origReminder.execute();

        boolean isSameObject = (origReminder == sameRef);
        boolean isIdUnchanged = "N-999".equals(origReminder.getId());
        boolean isDataUnchanged = "Submit Assignment".equals(origReminder.getText());
        boolean correctOutputs = "EMAIL [Header: Notification] Message: Submit Assignment".equals(beforeSwitch) && 
                                 "SMS: Submit Assignment".equals(afterSwitch);

        boolean passT5 = isSameObject && isIdUnchanged && isDataUnchanged && correctOutputs;
        String t5Details = "sameObject=" + isSameObject + " | stateUnchanged=" + (isIdUnchanged && isDataUnchanged) + 
                           " | before=" + beforeSwitch + " | after=" + afterSwitch;
        printCheck("T5", passT5, "Runtime Switch", t5Details, "sameObject=true | stateUnchanged=true | before=... | after=...");
        if (passT5) passCount++;

        // T6: A1 with I3 (PushChannel)
        Reminder reminder3 = new Reminder("N-101", "Meeting at 10 AM", new PushChannel());
        String resT6 = reminder3.execute();
        boolean passT6 = "PUSH [Badge: 1] Message: Meeting at 10 AM".equals(resT6);
        printCheck("T6", passT6, "Reminder + PushChannel", resT6, "PUSH [Badge: 1] Message: Meeting at 10 AM");
        if (passT6) passCount++;

        // T7: A2 with I3 (PushChannel)
        UrgentAlert alert3 = new UrgentAlert("N-202", "Server Down", new PushChannel());
        String resT7 = alert3.execute();
        boolean passT7 = "PUSH [Badge: 1] Message: URGENT: Server Down".equals(resT7);
        printCheck("T7", passT7, "UrgentAlert + PushChannel", resT7, "PUSH [Badge: 1] Message: URGENT: Server Down");
        if (passT7) passCount++;

        System.out.println("SUMMARY: " + passCount + "/7 PASS");
    }

    private static void printCheck(String id, boolean pass, String info, String actual, String expected) {
        if (pass) {
            System.out.println(id + " PASS | " + info + " | result=" + actual);
        } else {
            System.out.println(id + " FAIL | " + info + " | actual=" + actual + " | expected=" + expected);
        }
    }
}
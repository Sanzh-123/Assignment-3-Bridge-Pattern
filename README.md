## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

# Assignment 3: Bridge Pattern Implementation

Name: Sanzhar Abayev

Group: SE-2538

Topic Letter: Option B (Notifications)

Repository URL: https://github.com/Sanzh-123/Assignment-3-Bridge-Pattern

Base Commit Hash: 9b2ef94d41437c69ce7d41c18c43a0a13ed55414

## 1. Class Role Mapping

| Pattern Role | Class Name | Source File Path |
| :--- | :--- | :--- |
| **Abstraction** | `Notification` | `src/domain/Notification.java` |
| **A1 (Refined Abstraction)** | `Reminder` | `src/domain/Reminder.java` |
| **A2 (Refined Abstraction)** | `UrgentAlert` | `src/domain/UrgentAlert.java` |
| **Implementor** | `Channel` | `src/domain/Channel.java` |
| **I1 (Concrete Implementor)** | `EmailChannel` | `src/domain/EmailChannel.java` |
| **I2 (Concrete Implementor)** | `SmsChannel` | `src/domain/SmsChannel.java` |
| **I3 (Extension Implementor)**| `PushChannel` | `src/domain/PushChannel.java` |
| **Client / Demo Driver** | `Main` | `src/Main.java` |

## 2. Key Code References

- **Bridge Field:** `protected Channel channel;` in `src/domain/Notification.java` (Line 7)
- **`execute()` Method:** 
  - Defined abstractly in `src/domain/Notification.java` (Line 25)
  - Implemented in `src/domain/Reminder.java` (Line 18) and `src/domain/UrgentAlert.java` (Line 18)
- **`setImplementation(...)` Method:** `public void setImplementation(Channel channel)` in `src/domain/Notification.java` (Line 21)
- **T5 Check Location:** `src/Main.java` inside `runDemo()` method

---

## 3. Standard Build and Run Commands

Compile and run the project from the root folder without IDE dependencies using Java 17+:

```bash
# Compile command
javac --release 17 -encoding UTF-8 -d out "@sources.txt"

# Execution command
java -cp out Main --demo
```

---

## 4. Expected Demonstration Results (T1–T7)

```text
T1 PASS | Reminder + EmailChannel | result=EMAIL [Header: Notification] Message: Meeting at 10 AM
T2 PASS | Reminder + SmsChannel | result=SMS: Meeting at 10 AM
T3 PASS | UrgentAlert + EmailChannel | result=EMAIL [Header: Notification] Message: URGENT: Server Down
T4 PASS | UrgentAlert + SmsChannel | result=SMS: URGENT: Server Down
T5 PASS | Runtime Switch | result=sameObject=true | stateUnchanged=true | before=EMAIL [Header: Notification] Message: Submit Assignment | after=SMS: Submit Assignment
T6 PASS | Reminder + PushChannel | result=PUSH [Badge: 1] Message: Meeting at 10 AM
T7 PASS | UrgentAlert + PushChannel | result=PUSH [Badge: 1] Message: URGENT: Server Down
SUMMARY: 7/7 PASS
```
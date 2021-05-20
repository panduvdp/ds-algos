package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
###################     Editor Question Problem Description     #######################
    # Example convo string:
    '''
    Hi there - you are back soon!
    How are you today?
    Good
    Great to hear.
    Do you want to resume our conversation about self-compassion?
    Sure
    Woohoo! Let us begin.
    Not really
    Ok, some other time then.
    Ok
    Nice!
    Bad
    Sorry to hear that. Wanna keep chatting?
    Sure
    Cool! Let us talk about self-compassion.
    Maybe later
    Sounds good.
    '''
    # Other things to keep in mind:
    '''
    Validation Rules:
    BUTTONS are INVALID if:
    1) Text is longer than 20 chars
    LARK LINES are INVALID if:
    2) Text is longer than 100 chars
    3) Line has more than 3 associated buttons

    Color code:
    - BLACK: valid Lark line
    - GREEN: valid button
    - RED: invalid Lark line or invalid button

    Helper function (pseudocode, doesn't actually run) :
    printLine("Hi how are you", "red")
    '''
    ###################     End of Editor Question Problem Description
 */


public class LarkPhoneScreen {
  public static class LarkConversation {
    String text;
    List<Button> buttons;

    LarkConversation (String input) {
      text = input;
      buttons = new ArrayList();
    }

    public void addButton( Button button) {
      buttons.add(button);
    }

    public boolean validate() {
      if(buttons.size()> 3) {
        return false;
      }
      if(text.length()> 100) {
        return false;
      }
      return true;

    }
  }

  public static class Button {
    String text;
    List<LarkConversation> followUps;

    Button(String input) {
      text = input;
      followUps= new ArrayList<>();
    }

    public void addLarkConversation( LarkConversation conversation) {
      followUps.add(conversation);
    }

    public void addLarkConversations( List<LarkConversation> conversations) {
      followUps.addAll(conversations);
    }


    public boolean validate() {

      if(text.length()> 20) {
        return false;
      }
      return true;

    }
  }
  /**
   * It would differentiate between Lark text and button using number of preceding tabs
   * (even tabs are Lark text and odd tabs are Button text). Used Level info to determine
   * children conversation under the each button.
   */
  public static List<LarkConversation> printConversationRecursive(
      String[] lines, int start, int level, Set<Integer> processedLines) {

    List<LarkConversation> conversations = new ArrayList();
    LarkConversation previousLarkConversation = null;
    Button button = null;

    for (int i = start; i < lines.length; i++) {
      if (processedLines.contains(i)) {
        continue;
      }
      String[] tabs = lines[i].split(("\t"));
      int noOfTabs = 0;
      for (String temp : tabs) {
        if (temp.isEmpty()) {
          noOfTabs++;
        } else {
          break;
        }
      }
      // process current level - which is noOfTabs/2
      if (noOfTabs / 2 == level) {
        // if noOfTabs is even then it is Lark Line a
        if (noOfTabs % 2 == 0) {
          if (previousLarkConversation != null) {
            conversations.add(previousLarkConversation);
          }
          previousLarkConversation = new LarkConversation(lines[i]);
        } else {
          button = new Button(lines[i]);
          previousLarkConversation.addButton(button);
        }
        processedLines.add(i);
      } else if (noOfTabs / 2 == level + 1) { // if it is next level process recursively
        // starting new conversation under the button

        button.addLarkConversations(
            printConversationRecursive(lines, i, level + 1, processedLines));
      } else {
        //broke out of conversation loop
        break;
      }
    }
    if (previousLarkConversation != null && !conversations.contains(previousLarkConversation)) {
      conversations.add(previousLarkConversation);
    }
    return conversations;
    }

  public static void printLine(String text, String color) {
    System.out.println(color+ ":" + text);
  }

  public static void printButtonText(Button button) {
    if(button.validate()) {
      printLine(button.text, "GREEN");
    } else {
      printLine(button.text, "RED");
    }
    for(LarkConversation conversation: button.followUps) {
      printLarkConversation(conversation);
    }

  }
  public static void printLarkConversation(LarkConversation conversation) {
    if(conversation.validate()) {
      printLine(conversation.text, "BLACK");
    } else {
      printLine(conversation.text, "RED");
    }

    for(Button button: conversation.buttons) {
      printButtonText(button);
    }

  }
  public static void printConversation(String input) {
    if(input == null || input.length() == 0){
      return;
    }

    String[] lines = input.split("\n");
    Set<Integer> processedLines = new HashSet<>();

    List<LarkConversation> conversations = printConversationRecursive(lines, 0, 0, processedLines);

    for(LarkConversation conversation: conversations) {
      printLarkConversation(conversation);
    }
  }

  public static List<List<Integer>> findBeforeMatrix(List<List<Integer>> after) {
    // Write your code here
    if(after == null || after.size() == 0) {
      return after;
    }

    List<List<Integer>> before = new ArrayList<>();

    for(int i=0; i< after.size(); i++) {
      before.add(new ArrayList<>());
    }

    for(int i= after.size()-1; i > -1 ; i--) {
      for(int j = after.get(i).size()-1; j>-1; j--) {
        int temp = i>0 ? after.get(i-1).get(j):0;
        int temp1 = j>0 ? after.get(i).get(j-1): 0;
        int temp2 = i>0 && j>0 ? after.get(i-1).get(j-1) : 0;
        int prevValue = after.get(i).get(j) - (temp+temp1) + temp2;
        before.get(i).add(prevValue);
      }
    }

    for(int i =0; i<before.size(); i++ ) {
      Collections.reverse(before.get(i));
    }

    return before;


  }


  public static void main(String[] args) {

    final String response = "Hi there - you’re back soon!\n"
        + "How are you today?\n"
        + "\tGood\n"
        + "\t\tGreat to hear.\n"
        + "\t\tDo you want to resume our conversation about self-compassion?\n"
        + "\t\t\tSure\n"
        + "\t\t\t\tWoohoo! Let’s begin.\n"
        + "\t\t\tNot really\n"
        + "\t\t\t\tOk, some other time then.\n"
        + "\tOk\n"
        + "\t\tNice!\n"
        + "\tBad\n"
        + "\t\tSorry to hear that. Wanna keep chatting?\n"
        + "\t\t\tSure\n"
        + "\t\t\t\tCool! Let's talk about self-compassion.\n"
        + "\t\t\tMaybe later\n"
        + "\t\t\t\tSounds good.";

    printConversation(response);

  }

}

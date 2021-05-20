package ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class InMemoryDataStore {

  public static void main(String[] args) {
    DataStore dataStore = new DataStore();
    dataStore.set("charan", 1);
    System.out.println(dataStore.get("charan"));
    Transaction transaction = new Transaction();
    transaction.begin(dataStore);
    // transaction.unSet("charan");
    System.out.println(dataStore.get("charan"));
    // System.out.println(transaction.get("charan"));
    // transaction.commit();
    System.out.println(dataStore.get("charan"));

    Scanner myObj = new Scanner(System.in); // Create a Scanner object
    System.out.println("Enter Commmands (enter 'c' to exit");
    List<Command> userCommands = new ArrayList<>();
    boolean readNext = true;
    while (true) {
      String command = myObj.nextLine();
      if (command.equals("c")) {
        break;
      }
      try {
        userCommands.add(new Command(command));
      } catch (Exception e) {
        System.out.println("invalid command");
      }
    }
    System.out.println(userCommands);
  }

  enum CommandType {
    GET,
    SET,
    UNSET,
    EXISTS,
    BEGIN,
    ROLLBACK,
    COMMIT
  }

  public static class Command {
    private List<String> params;
    private CommandType type;

    public Command(String input) {
      validateAndInit(input);
    }

    private void validateAndInit(String input) {
      String[] command = input.split(" ");
      this.type = CommandType.valueOf(command[0]);
      this.params = Arrays.asList(command);
      this.params.remove(0);
    }
  }

  public static class DataStore {
    private Map<String, Integer> store;

    public DataStore() {
      this.store = new HashMap<>();
    }

    public DataStore(Map<String, Integer> store) {
      this.store = store;
    }

    public Integer get(final String key) {
      return this.store.getOrDefault(key, null);
    }

    public Integer set(final String key, final Integer value) {
      return this.store.put(key, value);
    }

    public Integer unSet(final String key) {
      return this.store.remove(key);
    }

    public boolean isExists(final String key) {
      return this.store.containsKey(key);
    }
  }

  public static class Transaction {
    //    private Map<String, Integer> updatedKVStore;
    //    private Set<String> deletedKeys;
    //    private Transaction child;
    //    private DataStore parent;
    private DataStore dataStore;

    private Stack<DataStore> transactions;

    public Transaction() {
      this.transactions = new Stack<>();
    }

    public DataStore begin(DataStore store) {
      this.transactions.push(store);
      this.dataStore = new DataStore(store.store);
      return this.dataStore;
    }

    public DataStore rollback() {
      return this.transactions.pop();
    }

    public DataStore commit(DataStore store) {
      this.transactions.pop();
      return this.dataStore;
    }
    //
    //    public void setChild(Transaction child) {
    //      this.child = child;
    //    }
    //
    //    public void removeChild(Transaction child) {
    //      this.child = null;
    //    }
  }

  public static class Operation {
    CommandType commandType;
    String query;

    public Operation(final CommandType commandType, final String query) {
      this.commandType = commandType;
      this.query = query;
    }

    public void execute() {}
  }

  public class QueryExecutor {

    private DataStore dataStore;
    private Transaction transaction;

    public QueryExecutor() {
      this.dataStore = new DataStore();
      this.transaction = new Transaction();
    }

    public void executeQuery(Command command) {
      CommandType commandType = command.type;

      switch (commandType) {
        case GET:
          System.out.println();
          this.dataStore.get(command.params.get(0));
          break;
      }
    }
  }
}

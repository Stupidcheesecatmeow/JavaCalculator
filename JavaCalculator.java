package com.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * JavaCalculator - A comprehensive calculator implemented in Java
 * Includes basic arithmetic, scientific functions, memory operations,
 * and calculation history tracking.
 */
public class JavaCalculator {
    
    // Inner class for history items
    private static class HistoryItem {
        private String operation;
        private String result;
        
        public HistoryItem(String operation, String result) {
            this.operation = operation;
            this.result = result;
        }
        
        public String getOperation() {
            return operation;
        }
        
        public String getResult() {
            return result;
        }
        
        @Override
        public String toString() {
            return operation + " = " + result;
        }
    }
    
    // Calculator state
    private double currentValue = 0;
    private double memoryValue = 0;
    private boolean hasMemory = false;
    private List<HistoryItem> history = new ArrayList<>();
    
    /**
     * Basic arithmetic operations
     */
    public double calculate(double a, double b, String operator) {
        double result;
        
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    System.out.println("Error: Cannot divide by zero");
                    return a;
                }
                result = a / b;
                break;
            case "^":
                result = Math.pow(a, b);
                break;
            default:
                System.out.println("Unknown operator: " + operator);
                return 0;
        }
        
        // Add to history
        String operation = formatNumber(a) + " " + formatOperator(operator) + " " + formatNumber(b);
        addToHistory(operation, formatNumber(result));
        
        return result;
    }
    
    /**
     * Perform calculation using current value
     */
    public double calculate(double b, String operator) {
        double result = calculate(currentValue, b, operator);
        currentValue = result;
        return result;
    }
    
    /**
     * Memory operations
     */
    public void memoryClear() {
        memoryValue = 0;
        hasMemory = false;
    }
    
    public void memoryStore(double value) {
        memoryValue = value;
        hasMemory = true;
    }
    
    public double memoryRecall() {
        return memoryValue;
    }
    
    public boolean hasMemory() {
        return hasMemory;
    }
    
    /**
     * Scientific functions
     */
    public double sqrt(double value) {
        double result = Math.sqrt(value);
        addToHistory("√(" + formatNumber(value) + ")", formatNumber(result));
        return result;
    }
    
    public double sin(double value) {
        double radians = Math.toRadians(value);
        double result = Math.sin(radians);
        addToHistory("sin(" + formatNumber(value) + "°)", formatNumber(result));
        return result;
    }
    
    public double cos(double value) {
        double radians = Math.toRadians(value);
        double result = Math.cos(radians);
        addToHistory("cos(" + formatNumber(value) + "°)", formatNumber(result));
        return result;
    }
    
    public double tan(double value) {
        double radians = Math.toRadians(value);
        double result = Math.tan(radians);
        addToHistory("tan(" + formatNumber(value) + "°)", formatNumber(result));
        return result;
    }
    
    public double log(double value) {
        double result = Math.log10(value);
        addToHistory("log(" + formatNumber(value) + ")", formatNumber(result));
        return result;
    }
    
    public double ln(double value) {
        double result = Math.log(value);
        addToHistory("ln(" + formatNumber(value) + ")", formatNumber(result));
        return result;
    }
    
    public double negate(double value) {
        double result = -value;
        addToHistory("-(" + formatNumber(value) + ")", formatNumber(result));
        return result;
    }
    
    public double percentage(double value) {
        double result = value / 100;
        addToHistory(formatNumber(value) + "%", formatNumber(result));
        return result;
    }
    
    /**
     * Helper methods
     */
    private String formatOperator(String operator) {
        switch (operator) {
            case "+": return "+";
            case "-": return "-";
            case "*": return "×";
            case "/": return "÷";
            case "^": return "^";
            default: return operator;
        }
    }
    
    private String formatNumber(double number) {
        if (Math.abs(number) < 1e-10) {
            return "0"; // Handle very small numbers that are effectively zero
        }
        
        if (number == (long) number) {
            return String.format("%d", (long) number);
        } else {
            return String.format("%.8g", number); // Use general format with reasonable precision
        }
    }
    
    private void addToHistory(String operation, String result) {
        HistoryItem item = new HistoryItem(operation, result);
        history.add(0, item); // Add to beginning of list
    }
    
    public List<HistoryItem> getHistory() {
        return history;
    }
    
    public void clearCalculator() {
        currentValue = 0;
    }
    
    public void showHistory() {
        System.out.println("\nCalculation History");
        System.out.println("===================");
        
        if (history.isEmpty()) {
            System.out.println("No calculations yet");
        } else {
            for (int i = 0; i < history.size(); i++) {
                HistoryItem item = history.get(i);
                System.out.println((i+1) + ". " + item.getOperation() + " = " + item.getResult());
            }
        }
        
        System.out.println("===================");
    }
    
    /**
     * Run a demonstration of calculator features
     */
    public void runDemonstration() {
        System.out.println("\n========================================");
        System.out.println("     JAVA CALCULATOR DEMONSTRATION     ");
        System.out.println("========================================");
        
        // Basic operations demo
        System.out.println("\n[BASIC OPERATIONS]");
        System.out.println("Addition: 5 + 3 = " + calculate(5, 3, "+"));
        System.out.println("Subtraction: 10 - 4 = " + calculate(10, 4, "-"));
        System.out.println("Multiplication: 7 × 6 = " + calculate(7, 6, "*"));
        System.out.println("Division: 20 ÷ 4 = " + calculate(20, 4, "/"));
        System.out.println("Power: 2 ^ 8 = " + calculate(2, 8, "^"));
        
        // Function operations demo
        System.out.println("\n[SPECIAL FUNCTIONS]");
        System.out.println("Square Root: √16 = " + sqrt(16));
        System.out.println("Trigonometry: sin(30°) = " + sin(30));
        System.out.println("Logarithm: log10(100) = " + log(100));
        
        // Additional functions demo
        System.out.println("\n[ADDITIONAL FEATURES]");
        double value = 50;
        System.out.println("Negate: -(" + value + ") = " + negate(value));
        System.out.println("Percentage: " + value + "% = " + percentage(value));
        
        // Memory operations
        System.out.println("\n[MEMORY OPERATIONS]");
        memoryStore(123.45);
        System.out.println("Stored 123.45 in memory");
        System.out.println("Memory recall: " + memoryRecall());
        
        // History
        System.out.println("\n[CALCULATION HISTORY]");
        showHistory();
        
        System.out.println("\nJava Calculator implementation complete!");
    }
    
    /**
     * Interactive console mode
     */
    public void runInteractiveMode() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("\n========================================");
        System.out.println("   JAVA CALCULATOR - INTERACTIVE MODE   ");
        System.out.println("========================================");
        System.out.println("Type 'help' for available commands or 'exit' to quit");
        
        clearCalculator();
        
        while (running) {
            System.out.print("\nCalculator> ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                running = false;
                System.out.println("Thank you for using Java Calculator!");
            } else if (input.equalsIgnoreCase("help")) {
                showHelp();
            } else if (input.equalsIgnoreCase("history")) {
                showHistory();
            } else if (input.equalsIgnoreCase("clear")) {
                clearCalculator();
                System.out.println("Calculator cleared");
            } else if (input.equalsIgnoreCase("demo")) {
                runDemonstration();
            } else if (input.equalsIgnoreCase("m+")) {
                memoryStore(currentValue);
                System.out.println("Stored " + formatNumber(currentValue) + " in memory");
            } else if (input.equalsIgnoreCase("mr")) {
                if (hasMemory()) {
                    currentValue = memoryRecall();
                    System.out.println("Recalled " + formatNumber(currentValue) + " from memory");
                } else {
                    System.out.println("Memory is empty");
                }
            } else if (input.equalsIgnoreCase("mc")) {
                memoryClear();
                System.out.println("Memory cleared");
            } else {
                processCalculationInput(input);
            }
        }
    }
    
    /**
     * Process a calculation input string
     */
    private void processCalculationInput(String input) {
        try {
            // Handle special functions
            if (input.startsWith("sqrt ") || input.startsWith("√ ")) {
                String valueStr = input.substring(input.indexOf(' ') + 1);
                double value = Double.parseDouble(valueStr);
                currentValue = sqrt(value);
                System.out.println("Result: " + formatNumber(currentValue));
                return;
            }
            
            if (input.startsWith("sin ")) {
                String valueStr = input.substring(4);
                double value = Double.parseDouble(valueStr);
                currentValue = sin(value);
                System.out.println("Result: " + formatNumber(currentValue));
                return;
            }
            
            if (input.startsWith("cos ")) {
                String valueStr = input.substring(4);
                double value = Double.parseDouble(valueStr);
                currentValue = cos(value);
                System.out.println("Result: " + formatNumber(currentValue));
                return;
            }
            
            if (input.startsWith("tan ")) {
                String valueStr = input.substring(4);
                double value = Double.parseDouble(valueStr);
                currentValue = tan(value);
                System.out.println("Result: " + formatNumber(currentValue));
                return;
            }
            
            if (input.startsWith("log ")) {
                String valueStr = input.substring(4);
                double value = Double.parseDouble(valueStr);
                currentValue = log(value);
                System.out.println("Result: " + formatNumber(currentValue));
                return;
            }
            
            if (input.endsWith("%")) {
                String valueStr = input.substring(0, input.length() - 1);
                double value = Double.parseDouble(valueStr);
                currentValue = percentage(value);
                System.out.println("Result: " + formatNumber(currentValue));
                return;
            }
            
            // Handle basic operations
            String[] parts = input.split("\\s+");
            if (parts.length == 1) {
                // Single number - just set it as current value
                currentValue = Double.parseDouble(parts[0]);
                System.out.println("Current value: " + formatNumber(currentValue));
            } else if (parts.length == 3) {
                // Full operation: a operator b
                double a = Double.parseDouble(parts[0]);
                String operator = parts[1];
                double b = Double.parseDouble(parts[2]);
                
                currentValue = calculate(a, b, operator);
                System.out.println("Result: " + formatNumber(currentValue));
            } else {
                System.out.println("Invalid input format. Try: 'number operator number' or 'function number'");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Show help information
     */
    private void showHelp() {
        System.out.println("\nJava Calculator Commands:");
        System.out.println("===========================");
        System.out.println("Basic operations: 5 + 3, 10 - 4, 7 * 6, 20 / 4, 2 ^ 8");
        System.out.println("Special functions: sqrt 16, sin 30, cos 45, tan 60, log 100");
        System.out.println("Percentage: 50%");
        System.out.println("Memory commands: m+ (store), mr (recall), mc (clear)");
        System.out.println("Other commands: history, clear, demo, help, exit");
        System.out.println("===========================");
    }
    
    /**
     * Main entry point
     */
    public static void main(String[] args) {
        JavaCalculator calculator = new JavaCalculator();
        
        if (args.length > 0 && args[0].equalsIgnoreCase("--demo")) {
            calculator.runDemonstration();
        } else {
            calculator.runInteractiveMode();
        }
    }
}



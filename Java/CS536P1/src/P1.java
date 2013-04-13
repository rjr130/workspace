///////////////////////////////////////////////////////////////////////////////
// Main Class File:  P1.java
// File:             P1.java
// Semester:         Fall 2013
//
// Author:           Junrui Ruan jruan@wisc.edu
// CS Login:         junrui
// Lecturer's Name:  Beck Hasti
// Lab Section:      none
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          none
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * This program is designed to test the Symbol and SymbolTable classes.
 * The tests have been broken up into several private helper methods.  The 
 * documentation for each helper method describes in more detail what tests
 * are done in that method.
 * 
 * Problems with Symbol and/or SymbolTable methods are reported to stderr.
 * 
 * @author Beck Hasti, copyright 2013
 */
public class P1 {
    public static void main(String[] args) {
        testSymbol();
        testExceptions();
        testInsert();
        testLookup();
        testPrint();
    }

    /**
     * Helper method for printing error messages. Prints to the standard 
     * error stream (stderr) in the following format:
     * 
     * ERROR[method]: msg
     * 
     * @param method Method in which error occurs
     * @param msg Associated message with further details about error
     */
    private static void error(String method, String msg) {
        System.err.println("ERROR[" + method + "]: " + msg);
    }

    /**
     * Tests the Symbol class.
     */
    private static void testSymbol() {

        // Test Symbol methods using different types, including the empty 
        // string
        String[] typeList = {"int", "double", ""};

        for (int i = 0; i < typeList.length; i++) {
            Symbol sym = new Symbol(new String(typeList[i]));

            String type = sym.getType();
            if (!type.equals(typeList[i])) {
                error("Symbol.getType", 
                      "returns wrong value when type is " + typeList[i]);
            }

            type = sym.toString();
            if (!type.equals(typeList[i])) {
                error("Symbol.toString", 
                      "returns wrong value when type is " + typeList[i]);
            }
        }

    }

    /**
     * Tests that the SymbolTable class throws EmptySymbolTableExceptions
     * and NullPointerExceptions under the correct conditions. 
     *  
     * Also tests:
     * addMap : add to an empty and non-empty SymbolTable
     * insert : insert into empty SymbolTable (i.e., has no HashMaps)
     * removeMap : remove from empty and non-empty SymbolTable
     * 
     * Note: testing DuplicateException happens in testInsert
     */
    private static void testExceptions() {
        SymbolTable symTab;

        // Test remove after initially calling constructor
        try {
            symTab = new SymbolTable();
            symTab.removeMap();  // this should NOT cause an exception
            try {
                symTab.removeMap();  // this SHOULD cause an exception
                error("SymbolTable.removeMap", 
                      "exception NOT thrown on empty SymbolTable");
            } catch (EmptySymbolTableException e) {
                // expected
            } catch (Exception e) {
                error("SymbolTable.removeMap",
                      "wrong exception thrown on empty SymbolTable");
            }
        } catch (EmptySymbolTableException e) {
            error("SymbolTable.removeMap", "EmptySymbolTableException thrown " +
                  "after calling SymbolTable constructor");
        } catch (Exception e) {
            error("SymbolTable.removeMap", e.toString() +
                  "thrown after calling SymbolTable constructor");
        }

        // Test remove after adding and removing some maps
        try {
            symTab = new SymbolTable();
            symTab.addMap();
            symTab.addMap(); // should have 3 maps now
            try {
                symTab.removeMap();
                symTab.removeMap();
                symTab.removeMap(); // now should have no maps
                try {
                    symTab.removeMap();  // should cause error
                    error("SymbolTable.removeMap", 
                          "exception NOT thrown on empty SymbolTable");
                } catch (EmptySymbolTableException e) {
                    // expected
                } catch (Exception e) {
                    error("SymbolTable.removeMap",
                          "wrong exception thrown on empty SymbolTable");
                }
            } catch (Exception e) {
                error("SymbolTable.removeMap", "unexpected exception " + 
                      e.toString() + "thrown on NON-empty SymbolTable");
            }
        } catch (Exception e) {
            error("SymbolTable.addMap", "unexpected exception " + e.toString());
        }

        // Test insert throws EmptySymbolTableException
        try {
            symTab = new SymbolTable();
            symTab.removeMap();
            try {
                symTab.insert("name", new Symbol("type"));
                error("SymbolTable.insert", 
                      "EmptySymbolTableException NOT thrown on empty SymbolTable");
            } catch (EmptySymbolTableException e) {
                // expected
            } catch (Exception e) {
                error("SymbolTable.insert", 
                      "wrong exception thrown on empty SymbolTable");
            }
        } catch (Exception e) {
            error("SymbolTable.removeMap", "unexpected exception " + 
                  e.toString() + "thrown on NON-empty SymbolTable");
        }

        // Test insert throws NullPointerException
        symTab = new SymbolTable();
        try {
            symTab.insert(null, new Symbol("type"));
            error("SymbolTable.insert", 
                  "NullPointerException NOT thrown on insert(null, sym)");
        } catch (NullPointerException e) {
            // expected
        } catch (Exception e) {
            error("SymbolTable.insert", 
                  "wrong exception thrown on insert(null, sym)");
        }

        try {
            symTab.insert("name", null);
            error("SymbolTable.insert", 
                  "NullPointerException NOT thrown on insert(name, null)");
        } catch (NullPointerException e) {
            // expected
        } catch (Exception e) {
            error("SymbolTable.insert", 
                  "wrong exception thrown on insert(name, null)");
        }

        try {
            symTab.insert(null, null);
            error("SymbolTable.insert", 
                  "NullPointerException NOT thrown on insert(null, null)");
        } catch (NullPointerException e) {
            // expected
        } catch (Exception e) {
            error("SymbolTable.insert", 
                  "wrong exception thrown on insert(null, null)");
        }
    }

    /**
     * Tests insert method of SymbolTable:
     * - insert into SymbolTable with 1 HashMap
     * - insert into SymbolTable with >1 HashMap
     * (note: insert into SymbolTable with 0 HashMaps done in testExceptions)
     * - insert >1 unique name
     * - insert of duplicate name in SymbolTable with one HashMap and with >1
     *   HashMap (where name is in 1st HashMap) (should cause exception in 
     *   both cases)
     * - insert of name already in a HashMap but not the 1st one (should NOT
     *   cause an exception)
     */
    private static void testInsert() {
        SymbolTable symTab;
        String name1 = "name1", name2 = "name2", name3 = "name3";
        Symbol sym1 = new Symbol("type1"), sym2 = new Symbol("type2");

        // Insert into SymbolTable with just one HashMap
        try {
            symTab = new SymbolTable();
            symTab.insert(name1, sym1);
            symTab.insert(name2, sym2);
            symTab.insert(name3, sym1);

            try {
                symTab.insert(name2, sym1);
                error("SymbolTable.insert", "exception NOT thrown when " +
                      "duplicate name added with 1 HashMap");
            } catch (DuplicateException e) {
                // expected
            } catch (Exception e) {
                error("SymbolTable.insert", "wrong exception thrown when " +
                      "duplicate name added with 1 HashMap");
            }
        } catch (Exception e) {
            error("SymbolTable.insert", "unexpected exception " + 
                  e.toString() + " with 1 HashMap");
        }

        // Insert into SymbolTable with >1 HashMap
        try {
            symTab = new SymbolTable();
            symTab.insert(name1, sym1);

            symTab.addMap();
            symTab.insert(name2, sym2);

            symTab.addMap();
            symTab.insert(name3, sym1);

            try {
                symTab.insert(name1, sym2); // should NOT throw exception

            } catch (DuplicateException e) {
                error("SymbolTable.insert", 
                      "exception thrown when name in another HashMap added");
            }

            try {
                symTab.insert(name3, sym2); // should throw exception
                error("SymbolTable.insert", "exception NOT thrown when " +
                      "duplicate name added with >1 HashMap");
            } catch (DuplicateException e) {
                // expected
            } catch (Exception e) {
                error("SymbolTable.insert", "wrong exception thrown when " +
                      "duplicate name added with >1 HashMap");
            }
        } catch (Exception e) {
            error("SymbolTable.insert", "unexpected exception " + 
                  e.toString() + " with >1 HashMap");
        }
    }

    /**
     * Tests localLookup and globalLookup methods of SymbolTable class.
     * 
     * localLookup: 
     * - both successful and failing lookups in a SymbolTable with just one 
     *   HashMap, and in a SymbolTable with multiple HashMaps (including a 
     *   case where localLookup should fail, but globalLookup should succeed)
     * - lookup after adding a name then calling removeMap
     * - also test localLookup in a SymTab with NO HashMap (should just return 
     *   null, no exception)
     *   
     * globalLookup: 
     * - both successful and failing lookups in a SymbolTable with just one 
     *   HashMap, and in a SymbolTable with multiple HashMaps, including cases 
     *   where the looked-up name is in the first HashMap, the last HashMap, 
     *   and some intermediate HashMap
     * - also test globalLookup in a SymbolTable with NO HashMap (should just 
     *   return null, no exception)
     */
    private static void testLookup() {
        Symbol sym, oneSym = new Symbol("int");
        SymbolTable symTab = new SymbolTable();
        String name;  

        // put one big try-catch around entire method to catch unexpected 
        // exceptions that happen with what should be normal removeMap and
        // insert operations
        try { 

            // test localLookup and globalLookup in a SymbolTable with no map
            symTab = new SymbolTable();
            symTab.removeMap();

            try {
                if (symTab.localLookup("aaa") != null) {
                    error("SymbolTable.localLookup",
                          "did not return null for SymbolTable with no maps");
                } 
            } catch (Exception e) {
                error("SymbolTable.localLookup", "unexpected exception for " +
                      "lookup in SymbolTable with no maps");
            }

            try {
                if (symTab.globalLookup("aaa") != null) {
                    error("SymbolTable.globalLookup", 
                          "did not return null for SymbolTable with no maps");
                } 
            } catch (Exception e) {
                error("SymbolTable.globalLookup", "unexpected exception for " +
                      "lookup in SymbolTable with no maps");
            }

            // test localLookup and globalLookup in a SymbolTable with one map
            symTab = new SymbolTable();
            if (symTab.localLookup("aaa") != null) {
                error("SymbolTable.localLookup", 
                      "null not returned for lookup of aaa in new SymbolTable");
            }

            if (symTab.globalLookup("aaa") != null) {
                error("SymbolTable.globalLookup",
                      "null not returned for lookup of aaa in new SymbolTable");
            }

            symTab.insert("aaa", oneSym);
            if (symTab.localLookup("aaa") == null) {
                error("SymbolTable.localLookup", 
                      "unexpected failure for table with 1 item ");
            }
            if (symTab.localLookup("a") != null) {
                error("SymbolTable.localLookup", 
                      "unexpected success for table with 1 item ");
            }

            if (symTab.globalLookup("aaa") == null) {
                error("SymbolTable.globalLookup", 
                      "unexpected failure for table with 1 item ");
            }
            if (symTab.globalLookup("a") != null) {
                error("SymbolTable.globalLookup", 
                      "unexpected success for table with 1 item ");
            }

            // test localLookup and globalLookup in a SymbolTable with four maps
            symTab.addMap();
            symTab.insert("bbb", oneSym);
            symTab.addMap();
            symTab.insert("ccc", oneSym);
            symTab.addMap();
            Symbol localSym = new Symbol("double");
            symTab.insert("ddd", localSym);
            if (symTab.localLookup("aaa") != null) {
                error("SymbolTable.localLookup", 
                      "null not returned for lookup of value aaa in 4th map");
            }
            if (symTab.globalLookup("aaa") != oneSym) {
                error("SymbolTable.globalLookup", 
                      "bad value returned for lookup of value aaa in 4th map");
            }

            if (symTab.localLookup("bbb") != null) {
                error("SymbolTable.localLookup", 
                      "null not returned for lookup of value bbb in 3rd map");
            }
            if (symTab.globalLookup("bbb") != oneSym) {
                error("SymbolTable.globalLookup", 
                      "bad value returned for lookup of value bbb in 3rd map");
            }

            if (symTab.localLookup("ddd") != localSym) {
                error("SymbolTable.localLookup", 
                      "bad value returned for lookup of value ddd in local map");
            }
            if (symTab.globalLookup("ddd") != localSym) {
                error("SymbolTable.globalLookup", 
                      "bad value returned for lookup of value ddd in local map");
            }

            // test local and global lookups after removing a map
            symTab.removeMap();
            if (symTab.localLookup("ddd") != null) {
                error("SymbolTable.localLookup", "null not returned for " +
                      "lookup of ddd after removing its table");
            }
            if (symTab.globalLookup("ddd") != null) {
                error("SymbolTable.globalLookup", "null not returned for " +
                      "lookup of ddd after removing its table");
            }

            // insert 10 items, look them all up both locally and globally,
            // both just after one insertion, and after all insertions
            symTab = new SymbolTable();
            name = "b";
            sym = new Symbol("float");
            for (int j=0; j<10; j++) {
                try {
                    symTab.insert(name, sym);
                } catch (DuplicateException e) {
                    error("SymbolTable.insert", "DuplicateException for " +
                          "table with 1 HashMap, multiple entries");
                } catch (EmptySymbolTableException e) {
                    error("SymbolTable.insert", "EmptySymbolTableException " +
                          "for table with 1 HashMap, multiple entries");
                }
                if (symTab.localLookup(name) == null) {
                    error("SymbolTable.localLookup", "unexpected failure " +
                          "for table with 1 HashMap, multiple entries");
                }
                else if (symTab.localLookup(name) != sym) {
                    error("SymbolTable.localLookup", "wrong value returned " +
                          "for table with 1 HashMap, multiple entries");
                }
                if (symTab.globalLookup(name) == null) {
                    error("SymbolTable.globalLookup", "unexpected failure " +
                          "for table with 1 HashMap, multiple entries");
                }
                else if (symTab.globalLookup(name) != sym) {
                    error("SymbolTable.globalLookup", "wrong value returned " +
                          "for table with 1 HashMap, multiple entries");
                }
                name += "b";
            }

            name = "b";
            for (int j=0; j<10; j++) {
                if (symTab.localLookup(name) == null) {
                    error("SymbolTable.localLookup", 
                          "unexpected failure for table with 1 HashMap, " +
                          "multiple entries (lookup after inserting all)");
                }
                if (symTab.globalLookup(name) == null) {
                    error("SymbolTable.globalLookup", 
                          "unexpected failure for table with 1 HashMap, " +
                          "multiple entries (lookup after inserting all)");
                }
                name += "b";
            }

            // SymbolTable with two HashMaps
            // add a new HashMap and try both local and global lookup
            // of entries in the old HashMap
            symTab.addMap();
            name = "b";
            if (symTab.globalLookup(name) != sym) {
                error("SymbolTable.globalLookup", "bad value returned for " +
                      "name in non-local HashMap of table with 2 HashMaps");
            }
            for (int j=0; j<10; j++) {
                if (symTab.localLookup(name) != null) {
                    error("SymbolTable.localLookup", 
                          "unexpected success for table with 2 HashMaps");
                }
                if (symTab.globalLookup(name) != sym) {
                    error("SymbolTable.globalLookup", 
                          "unexpected failure for table with 2 HashMaps ");
                }
                name += "b";
            }

            // add names that are already in the first HashMap to the new
            // HashMap; make sure that they can be inserted
            // and that they're found by both local and global lookup
            name = "b";
            for (int j=0; j<10; j++) {
                sym = new Symbol("float");
                try {
                    symTab.insert(name, sym);
                    if (symTab.localLookup(name) == null) {
                        error("SymbolTable.localLookup", 
                              "unexpected null for table with 2 HashMaps");
                    }
                    else if (symTab.localLookup(name) != sym) {
                        error("SymbolTable.localLookup", 
                              "wrong value returned for table with 2 HashMaps");
                    }
                    if (symTab.globalLookup(name) == null) {
                        error("SymbolTable.globalLookup", 
                              "unexpected failure for table with 2 HashMaps ");
                    }
                    else if (symTab.globalLookup(name) != sym) {
                        error("SymbolTable.globalLookup", 
                              "unexpected failure for table with 2 HashMaps");
                    }
                } catch (DuplicateException e) {
                    error("SymbolTable.insert", 
                          "unexpected DuplicateException for insert into " +
                          "table with 2 HashMaps");
                } catch (EmptySymbolTableException e) {
                    error("SymbolTable.insert", 
                          "unexpected EmptySymbolTableException for insert " +
                          "into table with 2 HashMaps");
                }
                name += "b";
            }

            // add names (to the 2nd HashMap) that are NOT in the 1st one
            // make sure both local and global lookup find them
            name = "c";
            for (int j=0; j<10; j++) {
                sym = new Symbol("int");
                try {
                    symTab.insert(name, sym);
                    if (symTab.localLookup(name) == null) {
                        error("SymbolTable.localLookup", 
                              "unexpected failure for table with 2 HashMaps, " +
                              "new name");
                    }
                    else if (symTab.localLookup(name) != sym) {
                        error("SymbolTable.localLookup", 
                              "bad value returned for table with 2 HashMaps, " +
                              "new name");
                    }
                    if (symTab.globalLookup(name) == null) {
                        error("SymbolTable.globalLookup", 
                              "unexpected failure for table with 2 HashMaps, " +
                              "new name");
                    }
                    else if (symTab.globalLookup(name) != sym) {
                        error("SymbolTable.globalLookup",
                              "bad value returned for table with 2 HashMaps, " +
                              "new name");
                    }
                } catch (DuplicateException e) {
                    error("SymbolTable.insert", 
                          "unexpected DuplicateException for table with 2 " +
                          "HashMaps, new name");
                } catch (EmptySymbolTableException e) {
                    error("SymbolTable.insert", 
                          "unexpected EmptySymbolTableException for table " +
                          "with 2 HashMaps, new name");
                }
                name += "b";
            }

            // SymbolTable with many HashMaps (20 names in each)
            for (int j=0; j<100; j++) {
                Integer tableint = new Integer(j);
                symTab.addMap();
                for (int k=0; k<20; k++) {
                    Integer symint = new Integer(k);
                    name = tableint.toString() + symint.toString();
                    sym = new Symbol("int");
                    try {
                        symTab.insert(name, sym);
                        if (symTab.localLookup(name) != sym) {
                            error("SymbolTable.localLookup", 
                                  "unexpected failure for table with many HashMaps");
                        }
                        if (symTab.globalLookup(name) != sym) {
                            error("SymbolTable.globalLookup", 
                                  "unexpected failure for table with many HashMaps ");
                        }
                    } catch (DuplicateException e) {
                        error("SymbolTable.insert", 
                              "DuplicateException for table with many HashMaps");
                    } catch (EmptySymbolTableException e) {
                        error("SymbolTable.insert", 
                              "EmptySymbolTableException for table with many HashMaps");
                    }
                }
            }

            // test globalLookup on one name from each HashMap
            for (int j=0; j<100; j++) {
                Integer tableint = new Integer(j);
                name = tableint.toString() + "6";
                if (symTab.globalLookup(name) == null) {
                    error("SymbolTable.globalLookup", 
                          "unexpected failure for table with many HashMaps " +
                          "(after all inserted)");
                }
            }

        } catch (Exception e) {
            error("SymbolTable", "unexpected exception " + e.toString() + 
                  " using insert/removeMap when testing lookup");
        }
    }

    /**
     * Tests the SymbolTable.print method by calling it with one empty HashMap,
     * with several non-empty HashMaps, and with no HashMaps.
     */
    private static void testPrint() {
        SymbolTable symTab = new SymbolTable();
        try {
            symTab.print();
            try {
                symTab.insert("name1", new Symbol("type1"));
                symTab.insert("name2", new Symbol("type2"));
                symTab.addMap();
                symTab.insert("name3", new Symbol("type3"));
            } catch (Exception e) {
                error("SymbolTable.insert", 
                      "unexpected exception " + e.toString());
            }
            symTab.print();
            try {
                symTab.removeMap();
                symTab.removeMap();
            } catch (Exception e) {
                error("SymbolTable.removeMap", 
                      "unexpected exception " + e.toString());
            }
            symTab.print();
        } catch (Exception e) {
            error("SymbolTable.print", 
                  "unexpected exception " + e.toString());
        }
    }
}

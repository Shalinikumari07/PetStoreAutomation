package api.utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class Dataprovider {

    // Define the DataProvider for login data
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        String path = System.getProperty("user.dir") + "//TestData//UserTestData.xlsx";
        XLUtility xl = new XLUtility(path);
        
        // Ensure correct sheet name in method calls
        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);
        
        String[][] apidata = new String[rownum][colcount];
        
        // Loop to populate array with data from Excel
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }
    
    // Define the DataProvider for usernames
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//TestData//UserTestData.xlsx";
        XLUtility xl = new XLUtility(path);
        
        int rownum = xl.getRowCount("Sheet1");
        String[] apidata = new String[rownum];
        
        // Loop to populate array with usernames
        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
        }
        return apidata;
    }
}

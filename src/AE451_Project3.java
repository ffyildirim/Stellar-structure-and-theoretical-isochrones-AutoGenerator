import java.io.*;

public class AE451_Project3 {

    public static void main(String[] args) throws FileNotFoundException {

        String inputFileName = "src/summary_100_met_0.0001.txt";
        String outputFileName = "src/M_T_summary_100_met_0.0001.txt";

        //creates a new file instance
        File inputFile = new File(inputFileName);
        File outputFile = new File(outputFileName);

        //reads the file
        FileReader fileReader;
        FileWriter fileWriter;

        try {

            fileReader = new FileReader(inputFile);

            //creates a buffering character input stream
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;

            if (outputFile.createNewFile()) {
                System.out.println("File created: " + outputFile.getName());
            }

            fileWriter = new FileWriter(outputFile);

            // this loop provide to reach all rows in the input file.
            while (true) {
                try {
                    // it breaks when reader reads all the file.
                    if ((line = br.readLine()) == null) break;
                    // it separates columns and puts them in an array.
                    String[] x = line.split("[ ]+");

                    // it provides the logarithmic version of luminosity.
                    double logLuminosityValue = Double.parseDouble(x[3].substring(0, x[3].length()-4)) *
                            Math.pow(10,-Double.parseDouble(x[3].substring(x[3].length()-1)));

                    // the formula is -2.5 * log(3.85 * 10^26).
                    // this formula is necessary to find the correct values of abs magnitude.
                    double luminosityOfSun = -2.5 * 26.58546073;

                    // it provides the Magnitude what we are looking for.
                    // the formula is -2.5 * log(L*L_sun) + 71.197425
                    // log can be separated as log(L) + log(L_sun)
                    double M = -2.5 * logLuminosityValue + luminosityOfSun + 71.197425;

                    // it provides the logarithmic version of temperature.
                    double logTemperatureValue = Double.parseDouble(x[5].substring(0, x[3].length()-5));

                    // it provides the Temperature what we are looking for.
                    double T = Math.pow(10, logTemperatureValue);

                    // it write the results to an output file.
                    fileWriter.write(T + "       " + M + "\n");


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println("There is an error: " + e);
        }
    }
}
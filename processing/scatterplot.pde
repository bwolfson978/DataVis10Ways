import org.gicentre.utils.stat.*;    // For chart classes.
 
// Simple scatterplot compating income and life expectancy.
 
XYChart scatterplot;
 
// Loads data into the chart and customises its appearance.
void setup()
{
  size(1000,500);   
  textFont(createFont("Arial",11),11);
 
  // Both x and y data set here.  
  scatterplot = new XYChart(this);
  
  // Load in data from a file 
  // (first line of file contains column headings).
  String[] data = loadStrings("cars-sample.csv");
  float[] mpg  = new float[data.length-1];
  float[] weight = new float[data.length-1];
      
  for (int i=0; i<data.length-1; i++)
  {
    String[] tokens = data[i+1].split(",");
    if(!tokens[3].equals("NA")){
      print(tokens[3]);
      mpg[i]  = Float.parseFloat(tokens[3]);   
      weight[i] = Float.parseFloat(tokens[7]);
    }
  }
  scatterplot.setData(weight, mpg);
  
  // Axis formatting and labels.
  scatterplot.showXAxis(true); 
  scatterplot.showYAxis(true); 
  scatterplot.setXFormat("###,###");
  scatterplot.setXAxisLabel("Weight");
  scatterplot.setYAxisLabel("MPG\n");
  scatterplot.setMinY(8);
  scatterplot.setMaxY(50);
  scatterplot.setMinX(1500);
  scatterplot.setMaxX(5000);
 
  // Symbol styles
  float[] size = new float[data.length-1];
  for (int i=0; i<data.length-1; i++)
  {
        size[i] = weight[i]/100;
  }
  scatterplot.setPointColour(color(180,50,50,100));
  scatterplot.setPointSize(weight, 10);
}
 
// Draws the scatterplot.
void draw()
{
  background(255);
  scatterplot.draw(20,20,width-40,height-40);
}
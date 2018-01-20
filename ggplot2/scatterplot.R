data = read.csv("~/cars-sample.csv")
ggplot(data, aes(x=Weight, y=MPG)) + geom_point(aes(size = Weight, colour = Manufacturer, alpha=0.5))

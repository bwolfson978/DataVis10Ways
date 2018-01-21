import csv
import numpy as np
import matplotlib.pyplot as plt

color_map = {
    "bmw":'red',
    "ford":'yellow',
    "honda":'green',
    "mercedes":'blue',
    "toyota":'purple'
}

with open('cars-sample.csv') as csvfile:
    data = list(csv.reader(csvfile, delimiter=','))
    weight = []
    mpg = []
    manufacturer = []
    colors = []
    for i in range(1, len(data)):
        weight.append(int(data[i][7]))
        if data[i][3] == 'NA':
            mpg.append(25)
        else:
            mpg.append(float(data[i][3]))
        manufacturer.append(data[i][2])
        colors.append(color_map[data[i][2]])

    x, y = np.array(weight), np.array(mpg)
    plt.scatter(x, y, s=x/100, c=colors, alpha=0.5)
    plt.xticks(np.arange(min(x)+351, max(x) + 1 + 351, 1000))
    plt.yticks(np.arange(min(y), max(y) + 1, 10))
    plt.show()

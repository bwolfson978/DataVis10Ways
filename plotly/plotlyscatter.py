import csv
import numpy as np
import plotly
import plotly.graph_objs as go

color_map = {
    "bmw":'red',
    "ford":'orange',
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
    trace = go.Scatter(
        x = weight,
        y = mpg,
        mode = 'markers',
        marker = dict(
            size = x/100,
            color = colors,
        )
    )
    data = [trace]
    layout = go.Layout(
        xaxis=dict(
            tick0=2000,
            dtick=1000
        ),
        yaxis=dict(
            tick0=10,
            dtick=10
        )
    )
    fig = go.Figure(data=data, layout=layout)
    plotly.offline.plot(fig, 'test')
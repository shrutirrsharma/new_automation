
var pie = new d3pie("pieChart", {
    "header": {
        "title": {
            "text": "PROYECTOS",
            "fontSize": 28,
            "font": "verdana"
        },
        "subtitle": {
            "text": "",
            "color": "#ccc",
            "fontSize": 18,
            "font": "verdana"
        },
        "titleSubtitlePadding": 7
    },
    "footer": {
        "text": "Fuente: yo",
        "color": "#ccc",
        "fontSize": 11,
        "font": "courier",
        "location": "bottom-center"
    },
    "size": {
        "canvasWidth": 682,
        "pieInnerRadius": "50%",
        "pieOuterRadius": "72%"
    },
    "data": {
        "content": [
            {
                "label": "Programación",
                "value": 6,
                "color": "#82ccfb"
            },
            {
                "label": "Blogging",
                "value": 2,
                "color": "#0e4a5a"
            },
            {
                "label": "Ilustración / Arte",
                "value": 6,
                "color": "#395197"
            },
            {
                "label": "Diseño Gráfico",
                "value": 5,
                "color": "#457cda"
            },
            {
                "label": "Rotulación",
                "value": 2,
                "color": "#b0dae1"
            },
            {
                "label": "Agricultura",
                "value": 1,
                "color": "#062831"
            },
            {
                "label": "Diseño Web",
                "value": 8,
                "color": "#2c8bb1"
            }
        ]
    },
    "labels": {
        "outer": {
            "pieDistance": 28
        },
        "inner": {
            "format": "none"
        },
        "mainLabel": {
            "font": "verdana",
            "fontSize": 17
        },
        "percentage": {
            "color": "#e1e1e1",
            "font": "verdana",
            "decimalPlaces": 0
        },
        "value": {
            "color": "#e1e1e1",
            "font": "verdana"
        },
        "lines": {
            "enabled": true,
            "style": "straight"
        }
    },
    "effects": {
        "pullOutSegmentOnClick": {
            "effect": "linear",
            "speed": 400,
            "size": 8
        }
    },
    "misc": {
        "pieCenterOffset": {
            "y": -15
        }
    },
    "callbacks": {}
});

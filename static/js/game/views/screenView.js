define(function(require) {
        var Backbone = require('backbone'),
            screen = require('game/models/screen'),
            ScreenView = Backbone.View.extend({
                initialize: function (canvas) {
                    this.model = screen;
                    this.canvas = canvas;
                },

                render: function () {
                    // TODO Рисует статический фон экрана
                }
            });
        return ScreenView;
    }
);

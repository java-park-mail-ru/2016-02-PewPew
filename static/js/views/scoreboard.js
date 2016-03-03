define(function (require) {
    var scoreboard = require('models/scoreboard'),
        baseView = require('views/baseView'),
        tmpl = require('tmpl/scoreboard');


    var View = baseView.extend({
        template: tmpl,
        render: function () {
            this.$el.html(this.template(this.model.getScores()));
        }
    });

    return new View({model: scoreboard});
});
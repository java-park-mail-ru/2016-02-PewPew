define(['views/main', 'views/game', 'views/login', 'views/scoreboard', 'views/register'],
    function () {
        var Backbone = require('backbone'),
            session = require('models/session');

        var Router = Backbone.Router.extend({
            routes: {
                'main': 'displayView',
                'login': 'displayView',
                'register': 'displayView',
                'scoreboard': 'displayView',
                'game': 'displayView',
                '*default': 'defaultAction'
            },
            initialize: function () {
                this.currentView = require('views/main');
                var event = require('event');
                this.listenTo(event, 'navigate', this.changeRoute);
                this.listenTo(event, 'startGame', this.startGame)
            },
            displayView: function () {
                var fragmentName = Backbone.history.getFragment();
                var view = require('views/'+fragmentName);
                this.currentView.hide();
                view.show();
                this.currentView = view;
            },
            defaultAction: function () {
                var mainView = require('views/main');
                mainView.show();
                this.currentView = mainView;
            },
            changeRoute: function (route) {
                this.navigate(route, {trigger: true});
            },
            startGame: function() {
                var view = require('views/game');
                this.currentView.hide();
                view.show();
                this.currentView = view;
            }
        });

        return new Router();
    }
);
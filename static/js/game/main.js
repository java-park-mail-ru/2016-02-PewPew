define(function(require) {
        var BulletsView = require('game/views/allBulletsView'),
            bulletsCollection = require('game/collections/bulletCollection'),
            barriersCollection = require('game/collections/barriersCollection'),
            BarriersView = require('game/views/allBarriersView'),
            PlayerView = require('game/views/playerView'),
            Player = require('game/models/player'),
            _ = require('underscore');
        return {
            init: function () {
                this.dynamicCanvas = document.getElementById('dynamicLayer');
                this.player = new Player('Guest', this.dynamicCanvas.width, this.dynamicCanvas.height);
                this.playerView = new PlayerView(this.player, this.dynamicCanvas);
                this.bulletsView = new BulletsView(bulletsCollection);
                this.barriersView = new BarriersView({collection : barriersCollection});
                var NUMBER_X = 16,
                    NUMBER_Y = 4,
                    RATIO = 0.3,
                    LEFT_CORNER_POS_X = 40,
                    LEFT_CORNER_POS_Y = 40;
                barriersCollection.createRandom(NUMBER_X, NUMBER_Y, RATIO, LEFT_CORNER_POS_X, LEFT_CORNER_POS_Y);
            },
            run: function() {
                requestAnimationFrame(_.bind(this.iterate, this));
            },
            iterate: function() {
                var context = this.dynamicCanvas.getContext('2d');
                context.clearRect(0, 0, this.dynamicCanvas.width, this.dynamicCanvas.height);
                this.bulletsView.render();
                this.barriersView.render();
                bulletsCollection.iterate(barriersCollection, this.dynamicCanvas.width);
                this.playerView.render();
                requestAnimationFrame(_.bind(this.iterate, this));
            }
        };
    }
);

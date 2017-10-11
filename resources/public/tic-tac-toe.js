function Game(params) {
    this.board = params["board"];
    this.size = params["size"];
    this.currentPlayer = params["current-player"];
    this.opponentPlayer = params["opponent-player"];
    this.state = params["state"]; 
    this.winner = params["winner"];
}

Game.prototype.update = function(params) {
    this.board = params["board"];
    this.size = params["size"];
    this.currentPlayer = params["current-player"];
    this.opponentPlayer = params["opponent-player"];
    this.state = params["state"]; 
    this.winner = params["winner"];
}

Game.prototype.isOver = function() {
    return this.state === "win" || this.state === "draw";
}

Game.prototype.isSpaceAvailable = function(space) {
    return !this.isOver() && this.board[space-1] == "_";
}

function GameContext(params) {
    this.game = new Game(params); 
    this.gui = new GUI(document.getElementById("game").getContext('2d'));
    this.width = 500;
    this.height = 500;
    this.listenForClick = true;
}

GameContext.prototype.init = function() {
    document.getElementById("game").addEventListener('click', this.handleClick.bind(this));
    this.gui.render(this.game, this.width, this.height);
}

GameContext.prototype.handleClick = function(e) {
    let space = this.gui.getCell(e.offsetX, e.offsetY, this.width, this.height, this.game.size);
    if (!this.listenForClick || !this.game.isSpaceAvailable(space)) {
        return;
    }

    this.listenForClick = false;
    let that = this;

    let body = {"current-player": that.game.currentPlayer,
                "opponent-player": that.game.opponentPlayer,
                "board": that.game.board,
                "move": space}

    fetch("http://localhost:3000/game", {
        headers: {"Content-Type": "application/json"},
        method: "PUT",
        body: JSON.stringify(body) 
    }).then(function(response) {
        return response.json();   
    }).then(function(json) {
        that.game.update(json);
        that.gui.render(that.game, that.width, that.height)
        that.listenForClick = true;
    });
}

function start() {
    let form = document.getElementById("gameSetupForm");
    let opponent = form.opponent.value;
    let size = form.size.value;

    fetch("http://localhost:3000/game?opponent=" + opponent + "&size=" + size, {
        method: "POST"
    }).then(function(response) {
        return response.json();
    }).then(function(params) {
        let gameContext = new GameContext(params);
        gameContext.init();

        document.getElementById("startButton").remove();
    });
}


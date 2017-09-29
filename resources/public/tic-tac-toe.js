// tic-tac-toe.js

function Game(board, currentPlayer, opponentPlayer) {
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.opponentPlayer = opponentPlayer;
    this.state = null;
    this.winner = null;
}

Game.prototype.isOver = function() {
    return this.state === "win" || this.state === "draw";
}

Game.prototype.gameOverMessage = function() {
    if (!this.isOver()) {
        return null;
    }
    return this.state === "win" ? 
        "Player " + this.winner + " wins!" :
        "Cats game.";
}

Game.prototype.advance = function(space, callback) {
    if (this.isOver() || this.board[space-1] !== "_") {
        return;
    };

    let that = this;
    let body = {
        "current-player": this.currentPlayer,
        "opponent-player": this.opponentPlayer,
        "board": this.board,
        "move": space
    }

    fetch("http://localhost:3000/game", {
        headers: {"Content-Type": "application/json"},
        method: "PUT",
        body: JSON.stringify(body) 
    }).then(function(response) {
        return response.json();   
    }).then(function(json) {
        that.board =  json["board"];
        that.currentPlayer = json["current-player"];
        that.opponentPlayer = json["opponent-player"];
        that.state = json["state"];
        that.winner = json["winner"];
        callback.call();
    });
}


function GameContext(gameParams) {
    this.game = new Game(gameParams["board"], 
                         gameParams["current-player"],
                         gameParams["opponent-player"]);
    this.canvas = document.getElementById("gameCanvas"); 
    this.display = new Display(this.canvas.getContext('2d'), this.canvas.width, this.canvas.height);
}

GameContext.prototype.init = function() {
    this.canvas.addEventListener('click', this.handleClick.bind(this));
    this.display.render(this.game);
}

GameContext.prototype.handleClick = function(e) {
    let space = this.display.getCell(e.offsetX, e.offsetY);
    let that = this;
    this.game.advance(space, function() {
        that.display.render(that.game)
    });
}

function start() {
    let form = document.getElementById("gameSetupForm");
    let opponent = form.opponent.value;

    fetch("http://localhost:3000/game?opponent=" + opponent, {
        method: "POST"
    }).then(function(response) {
        return response.json();
    }).then(function(gameParams) {
        let gameContext = new GameContext(gameParams);
        gameContext.init();

        document.getElementById("startButton").remove();
    });
}


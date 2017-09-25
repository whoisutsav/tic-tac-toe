// tic-tac-toe.js

function Game(board, marker, opponent) {
    this.board = board;
    this.marker = marker;
    this.opponent = opponent;
    this.status = null;
    this.winner = null;
}

Game.prototype.isOver = function() {
    return this.status === "WIN" || this.status === "DRAW";
}

Game.prototype.gameOverMessage = function() {
    if (!this.isOver()) {
        return null;
    }
    return this.status === "WIN" ? 
        "Player " + this.winner + " wins!" :
        "Cats game.";
}

Game.prototype.advance = function(space, callback) {
    if (this.isOver() || this.board[space-1] !== "_") {
        return;
    };

    let that = this;
    let params = new URLSearchParams([
        ["marker", this.marker],
        ["opponent", this.opponent],
        ["move", space]
    ]);
    this.board.forEach(function(element) {
        params.append("board", element);
    });
    fetch("http://localhost:3000/move", {
        method: "POST",
        body: params
    }).then(function(response) {
        return response.json();   
    }).then(function(json) {
        that.board =  json.board;
        that.status = json.status;
        that.winner = json.winner;
        callback.call();
    });
}


function GameContext(gameParams) {
    this.game = new Game(gameParams.board, gameParams.marker, gameParams.opponent);
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

    fetch("http://localhost:3000/new-game?opponent=" + opponent).then(function(response) {
        return response.json();
    }).then(function(gameParams) {
        let gameContext = new GameContext(gameParams);
        gameContext.init();

        document.getElementById("startButton").remove();
    });
}


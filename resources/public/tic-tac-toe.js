const boardWidth = 500;
const boardFont = '30px sans-serif';
const win = "win";
const draw = "draw";

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
    return this.state === win || this.state === draw;
}

Game.prototype.isSpaceAvailable = function(space) {
    return this.board[space-1] == "_";
}

function Controller(game) {
    this.game = game; 
    this.active = true;
}

Controller.prototype.init = function() {
    document.getElementById("game").addEventListener('click', this.handleClick.bind(this));
    render(this.game);
}

Controller.prototype.handleClick = function(e) {
    let space = getSpace(e.offsetX, e.offsetY, this.game.size);
    if (!this.active || !this.game.isSpaceAvailable(space)) {
        return;
    }

    this.active = false;
    let body = {"current-player": this.game.currentPlayer,
                "opponent-player": this.game.opponentPlayer,
                "board": this.game.board,
                "move": space}

    let that = this;
    fetch("http://localhost:3000/game", {
            headers: {"Content-Type": "application/json"},
            method: "PUT",
            body: JSON.stringify(body) })
        .then(function(response) { return response.json();})
        .then(function(json) {
            that.game.update(json);
            render(that.game)
            if (!that.game.isOver()) { that.active = true; }
    });
}

function getSpace(x, y, size) {
    let cellWidth = boardWidth/size;
    let row = Math.floor(y/cellWidth);
    let column = Math.floor(x/cellWidth);
    return row * size + column + 1;
}

function render(game) {
    let ctx = document.getElementById("game").getContext('2d');
    ctx.clearRect(0, 0, boardWidth, boardWidth);
    ctx.font = boardFont;
    ctx.textAlign = "center";

    let cellWidth = boardWidth/game.size;
    for(let i=0; i< game.size; i++) {
        for(let j = 0; j < game.size; j++) {
            let x = i * cellWidth;
            let y = j * cellWidth;
            let marker = game.board[(j * game.size) + i];

            ctx.strokeRect(x, y, cellWidth, cellWidth);
            ctx.fillText(marker, x + cellWidth/2, y + cellWidth/2);
        }
    }
    gameOverMessage(game);
}

function gameOverMessage(game) {
    if (game.isOver()) {
        let message = game.state === win ? 
        "Player " + game.winner + " wins!" :
        "Cats game.";
        alert(message);
    }
}

function start() {
    let form = document.getElementById("gameSetupForm");
    let opponent = form.opponent.value;
    let size = form.size.value;

    fetch("http://localhost:3000/game?opponent=" + opponent + "&size=" + size, {
            method: "POST" })
        .then(function(response) { return response.json(); })
        .then(function(params) {
            let game = new Game(params);
            let controller = new Controller(game);
            controller.init();
            document.getElementById("startButton").remove();
    });
}

// tic-tac-toe.js

const CANVAS_WIDTH=500;
const CANVAS_HEIGHT=500;

function Game(board, marker, opponent) {
    this.board = board;
    this.marker = marker;
    this.opponent = opponent;
    this.status = null;
}

Game.prototype.render = function() {
    let ctx = document.getElementById('game').getContext('2d');
    ctx.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

    this.board.forEach(function(value, index) {
        let row = Math.floor(index/3);
        let column = index % 3;
        let x_offset = column * CANVAS_WIDTH/3;
        let y_offset = row * CANVAS_HEIGHT/3;

        ctx.strokeRect(x_offset, y_offset, CANVAS_WIDTH/3, CANVAS_HEIGHT/3);
        ctx.fillText(value, x_offset + CANVAS_WIDTH/6, y_offset + CANVAS_HEIGHT/6);
    });

    if (this.status == "WIN" || this.status == "DRAW") {
        alert(this.status);
    }
}

Game.prototype.move = function(space) {
    let that = this;
    let params = new URLSearchParams();
    this.board.forEach(function(element) {
        params.append("board", element);
    });
    params.set("marker", this.marker);
    params.set("opponent", this.opponent);
    params.set("move", space);
    fetch("http://localhost:3000/move", {
        method: "POST",
        body: params
    }).then(function(response) {
        return response.json();   
    }).then(function(json) {
        // TODO add end game logic
        that.board = json.board;
        that.status = json.status;
        that.render();
    });
}

Game.prototype.handleClick = function(e) {
    let row = Math.floor(e.offsetY / (CANVAS_HEIGHT/3));
    let column = Math.floor(e.offsetX / (CANVAS_WIDTH/3));
    let space = row * 3 + column + 1;

    if (this.board[space-1] === "_") {
        this.move(space);
    }
}

function init() {
    fetch("http://localhost:3000/new-game").then(function(response) {
        return response.json();
    }).then(function(json) {
        let game = new Game(
            json['board'],
            json['marker'],
            json['opponent']
        ); 
        game.render();
        document.getElementById('game').addEventListener('click', game.handleClick.bind(game));
    });
}

if (document.readyState === 'complete') init();
else window.addEventListener('load', init);


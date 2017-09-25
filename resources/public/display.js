// display.js

function Display(ctx, width, height) {
    this.ctx = ctx;
    this.width = width;
    this.height = height;
}

Display.prototype.render = function(game) {
    this.ctx.clearRect(0, 0, this.width, this.height);
    this.ctx.font = '30px sans-serif';
    this.ctx.textAlign = "center";

    for(let i=0; i<game.board.length; i++) {
        let xPos = (i % 3) * this.width/3;
        let yPos = Math.floor(i/3) * this.height/3;
        let marker = game.board[i];

        this.ctx.strokeRect(xPos, yPos, this.width/3, this.height/3);
        this.ctx.fillText(marker, xPos + this.width/6, yPos + this.height/6);
    }

    if (game.isOver()) {
        alert(game.gameOverMessage());
    }
}

Display.prototype.getCell = function(x, y) {
    let row = Math.floor(y / (this.height/3));
    let column = Math.floor(x / (this.width/3));
    return row * 3 + column + 1;
}


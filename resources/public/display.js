// display.js

function Display(ctx, width, height) {
    this.ctx = ctx;
    this.width = width;
    this.height = height;
}

Display.prototype.render = function(game) {
    let size = game.size;
    this.ctx.clearRect(0, 0, this.width, this.height);
    this.ctx.font = '30px sans-serif';
    this.ctx.textAlign = "center";

    for(let i=0; i<game.board.length; i++) {
        let xPos = (i % size) * this.width/size;
        let yPos = Math.floor(i/size) * this.height/size;
        let marker = game.board[i];

        this.ctx.strokeRect(xPos, yPos, this.width/size, this.height/size);
        this.ctx.fillText(marker, xPos + this.width/(size*2), yPos + this.height/(size*2));
    }

    if (game.isOver()) {
        alert(game.gameOverMessage());
    }
}

Display.prototype.getCell = function(x, y, size) {
    let row = Math.floor(y / (this.height/size));
    let column = Math.floor(x / (this.width/size));
    return row * size + column + 1;
}


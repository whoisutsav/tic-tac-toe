function GUI(ctx) {
    this.ctx = ctx;
}

GUI.prototype.render = function(game, width, height) {
    let size = game.size;
    this.ctx.clearRect(0, 0, width, height);
    this.ctx.font = '30px sans-serif';
    this.ctx.textAlign = "center";

    for(let i=0; i<game.board.length; i++) {
        let xPos = (i % size) * width/size;
        let yPos = Math.floor(i/size) * height/size;
        console.log(xPos);
        console.log(yPos);
        console.log(width);
        console.log(height);
        console.log(size);
        let marker = game.board[i];

        this.ctx.strokeRect(xPos, yPos, width/size, height/size);
        this.ctx.fillText(marker, xPos + width/(size*2), yPos + height/(size*2));
    }


    if (game.isOver()) {
        let message = game.state === "win" ? 
        "Player " + game.winner + " wins!" :
        "Cats game.";
        alert(message);
    }
}

GUI.prototype.getCell = function(x, y, width, height, size) {
    let row = Math.floor(y / (height/size));
    let column = Math.floor(x / (width/size));
    return row * size + column + 1;
}


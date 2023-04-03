//Write all the needed variables
const cells = document.querySelectorAll(".cell");
const statusText = document.querySelector("#statusText");
const restartBtn = document.querySelector("#restartBtn");
const winConditions = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6]
];
let options =["", "", "", "", "", "", "", "", ""];
let currentPlayer = "X";
let running = false;
    

//Start the game
initializeGame();

//write all the functions
function initializeGame(){
    cells.forEach(cell => cell.addEventListener("click", cellClicked));
    restartBtn.addEventListener("click", restartGame);
    statusText.textContent = `${currentPlayer}'s turn`;
    running = true;
}
function cellClicked() {
    const cellIndex = this.getAttribute("cellIndex");
    if (options[cellIndex] != "" || !running) {
      return;
    }
    updateCell(this, cellIndex);
    checkWinner();
    bot(); // add this line to call the bot function
  }
function updateCell(cell, index){
    options[index] = currentPlayer;
    cell.textContent = currentPlayer;
}
function changePlayer(){
    currentPlayer = (currentPlayer == "X")? "O":"X";
    statusText.textContent = `${currentPlayer}'s turn`;

}
function checkWinner(){
    let roundWon = false;

    for(let i =0; i < winConditions.length; i++){
        const condition = winConditions[i];
        const cellA = options[condition[0]];
        const cellB = options[condition[1]];
        const cellC = options[condition[2]];

        if(cellA == "" || cellB == "" || cellC == ""){
            continue;
        }
        if(cellA == cellB && cellB == cellC){
            roundWon = true;
            break;
        }
    }
    if(roundWon){
        statusText.textContent = `${currentPlayer} wins!`;
        running = false;
    }
    else if(!options.includes("")){
        statusText.textContent = `Draw!`;
        running = false;
    }
    else{
        changePlayer();
    }
}
function restartGame(){
    currentPlayer = "X";
    options =["", "", "", "", "", "", "", "", ""];
    statusText.textContent = `${currentPlayer}'s turn`;
    cells.forEach(cell => cell.textContent = "");
    running = true;
}
const bot = () => {
    let url = "http://localhost:8080/api/bot";
    reqConfig = {
      method: "POST",
      headers: { 
        "Content-Type": "application/json;charset=UTF-8",
        "Authorization": "Basic YWRtaW46MTIz"
    },
      body: JSON.stringify(options),
    };
    fetch(url, reqConfig)
      .then((response) => {
        return response.json();
      })
      .then((parsedResponse) => {
        const botMove = parsedResponse.botMove;
        const cellIndex = botMove;
        const cell = document.querySelector(`[cellIndex="${cellIndex}"]`);
       updateCell(cell, cellIndex);
       checkWinner();
      });
  };
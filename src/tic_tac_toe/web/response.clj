(ns tic-tac-toe.web.response)

(defn win-response [board winner]
  {
   "game_over" true 
   "message" (str (name winner) " wins")
   "board" board
   }
  )

(defn draw-response [board]
  {
   "game_over" true
   "message" "Cats game." 
   "board" board
   }
  )

(defn error-response [])

; TODO hide internals of the board
(defn game-response [{:keys [board current-player opponent-player] :as game}] 
  {
   "game_over" false
   "board" board
   "current-player" current-player
   "opponent-player" opponent-player
   }
  )

(ns tic-tac-toe.game)

(defn make-game [players board]
  (let [[main-player opponent-player] players] 
    {:current-player main-player 
     :opponent-player opponent-player
     :board board }))


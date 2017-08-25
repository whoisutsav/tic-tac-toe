(ns tic-tac-toe.console-game-setup
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.player-setup :as player-setup]
            [tic-tac-toe.board :as board])) 


;TODO remove hard-coded choices 
(defn get-game-type [options]
  (console-ui/print-menu options)
  (let [choice (read-string (console-ui/get-user-input))] 
    (case choice 
      1 :human-vs-human
      2 :human-vs-computer 
      3 :human-vs-hard-computer 
      (do (console-ui/print-message "Bad Option") (recur options)))))

(defn- construct-game [board-size players]
  (let [[current-player opponent-player] players
        board (board/new-board board-size)] 
    {:board board 
     :current-player current-player 
     :opponent-player opponent-player}))

;TODO move high-level player initialization logic into console-game-setup, so level of abstraction is consistent
(defn new-game [configuration]
  (let [{:keys [board-size options]} configuration
        game-type (get-game-type options)]
    (->> (player-setup/initialize-players game-type)
         (construct-game board-size))))


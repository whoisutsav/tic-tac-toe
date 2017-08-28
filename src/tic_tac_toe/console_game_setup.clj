(ns tic-tac-toe.console-game-setup
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.player-setup :as player-setup]
            [tic-tac-toe.board :as board])) 


;TODO remove hard-coded choices 
;TODO rename game-type to something else
(defn get-game-type [options]
  (console-ui/print-menu options)
  (let [choice (read-string (console-ui/get-user-input))] 
    (case choice 
      1 [:human :human]
      2 [:human :computer] 
      3 [:human :hard-computer] 
      (do (console-ui/print-message "Bad Option") (recur options)))))

(defn setup-players [game-type]
  (let [[main-player-type opponent-player-type] game-type
        main-player (player-setup/initialize-player main-player-type)
        opponent-player (player-setup/initialize-player opponent-player-type (:marker main-player))]
    [main-player opponent-player]))

(defn- make-game [players board]
  (let [[main-player opponent-player] players] 
    {:current-player main-player 
     :opponent-player opponent-player
     :board board }))

;TODO rename new-game/make-game
(defn new-game [configuration]
  (let [{:keys [board-size options]} configuration
        game-type (get-game-type options)
        players (setup-players game-type)
        board (board/new-board board-size)]
    (make-game players board)))


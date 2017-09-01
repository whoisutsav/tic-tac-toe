(ns tic-tac-toe.console-game-setup
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.player-setup :as player-setup]
            [tic-tac-toe.board :as board])) 


;TODO remove hard-coded choices 
(defn- get-game-type [options]
  (console-ui/print-menu options)
  (let [choice (read-string (console-ui/get-user-input))] 
    (case choice 
      1 [:human :human]
      2 [:human :computer] 
      3 [:human :hard-computer] 
      (do (console-ui/print-message "Bad Option") (recur options)))))

(defn- get-players [game-type]
  (let [[main-player-type opponent-player-type] game-type
        main-player (player-setup/setup-new main-player-type)
        opponent-player (player-setup/setup-new opponent-player-type (:marker main-player))]
    [main-player opponent-player]))

(defn- make-game [players board]
  (let [[main-player opponent-player] players] 
    {:current-player main-player 
     :opponent-player opponent-player
     :board board }))

(defn setup-new [configuration]
  (let [{:keys [board-size options]} configuration
        game-type (get-game-type options)
        players (get-players game-type)
        board (board/new-board board-size)]
    (make-game players board)))


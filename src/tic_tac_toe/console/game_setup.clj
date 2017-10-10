(ns tic-tac-toe.console.game-setup
  (:require [tic-tac-toe.console.ui :as console-ui]
            [tic-tac-toe.console.player-setup :as player-setup]
            [tic-tac-toe.core.board :as board])) 

(def invalid-option-message "Invalid option")

(defn- invalid-option []
  (console-ui/print-message invalid-option-message)
  (console-ui/get-user-input))

(defn- game-type-loop [options]
  (console-ui/print-menu options)
  (loop [choice (console-ui/get-user-input)] 
    (case (read-string choice) 
      1 [:human :human]
      2 [:human :computer] 
      3 [:human :hard-computer] 
      (recur (invalid-option)))))

(defn- get-players [game-type]
  (let [[main-type opponent-type] game-type
        main-player (player-setup/setup-new main-type)
        opponent-player (player-setup/setup-new opponent-type (:marker main-player))]
    [main-player opponent-player]))

(defn make-game [players board]
  (let [[main-player opponent-player] players] 
    {:current-player main-player 
     :opponent-player opponent-player
     :board board }))

(defn setup-new [configuration]
  (let [{:keys [board-size options]} configuration
        players (->> (game-type-loop options)
                     (get-players)) 
        board (board/new-board board-size)]
    (make-game players board)))


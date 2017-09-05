(ns tic-tac-toe.console-game-setup
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.player-setup :as player-setup]
            [tic-tac-toe.board :as board])) 

(def invalid-option-message "Invalid option")

(defn- game-type-loop [options]
  (console-ui/print-menu options)
  (loop [choice (console-ui/get-user-input)] 
    (case (read-string choice) 
      1 [:human :human]
      2 [:human :computer] 
      3 [:human :hard-computer] 
      (do (console-ui/print-message invalid-option-message) (recur options)))))

(defn- get-players [player-types]
  (let [[main-player-type opponent-player-type] player-types
        main-player (player-setup/setup-new main-player-type)
        opponent-player (player-setup/setup-new opponent-player-type (:marker main-player))]
    [main-player opponent-player]))

(defn- make-game [players board]
  (let [[main-player opponent-player] players] 
    {:current-player main-player 
     :opponent-player opponent-player
     :board board }))

(defn setup-new [configuration]
  (let [{:keys [board-size player-options]} configuration
        players (->> (game-type-loop player-options)
                     (get-players)) 
        board (board/new-board board-size)]
    (make-game players board)))


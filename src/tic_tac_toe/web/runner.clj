(ns tic-tac-toe.web.runner
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.basic-game :as basic-game]
            [tic-tac-toe.player.human-web] ;TODO remove
            [tic-tac-toe.player.hard-computer] ;TODO remove
            ))

(defn add-meta [params winner state]
  (assoc params :winner winner :state state))

(defn- default-response [params]
  (add-meta params nil "active"))

(defn- win-response [params winner]
  (add-meta params winner "win")) 

(defn- draw-response [params]
  (add-meta params nil "draw"))

(defn- end-game [game]
  (if-let [winner (decision/winner (:board game))]
    (win-response game winner)
    (draw-response game)))

(declare update-game)

(defn- recur-update [game]
  (let [current-player (:type (:current-player game))] 
    (if (or (= :computer current-player) (= :hard-computer current-player))
      (update-game game)
      (default-response game))))

(defn update-game [game]
  (let [updated-game (basic-game/take-turn game)]
      (if (decision/over? (:board updated-game)) 
        (end-game updated-game)
        (recur-update updated-game))))

(defn new-game [opponent-type]
  (let [board (board/new-board 3)]
    (-> {:board board
         :current-player {:type :human-web :marker :X}
         :opponent-player {:type opponent-type :marker :O}}
        (default-response))))


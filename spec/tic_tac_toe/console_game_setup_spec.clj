(ns tic-tac-toe.console-game-setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console-game-setup :refer :all]))

(def empty-board [ :_ :_ :_
                   :_ :_ :_
                   :_ :_ :_ ])

(def configuration {:board empty-board

                   :options ["1. Human vs. Human"
                             "2. Human vs. Computer"]})

(describe "new-game"
          (around [it] (with-out-str (it)))

          (it "initializes human vs human game"
              (should= {
                        :board empty-board
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :human}}
                       (with-in-str "1\nX\nO\n" (new-game configuration))))

          (it "initializes human vs computer game"
              (should= {
                        :board empty-board 
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :computer}}
                       (with-in-str "2\nX\n" (new-game configuration))))

          (it "disregards bad input"
              (should= {
                        :board empty-board 
                        :current-player {:marker :X :type :human}
                        :opponent-player {:marker :O :type :computer}}
                       (with-in-str "17\n..\n FiVe \n2\nX\n" (new-game configuration)))))

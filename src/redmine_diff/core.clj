(ns redmine-diff.core
  (:gen-class))

(use '[clojure.java.shell :only [sh]])
(require '[clojure.string :as str])

(def task-url "http://redmine/issue/")

(defn- git-exec
  [path branch1 branch2]
  (->
    (sh "git" "--no-pager" "log" "--pretty=oneline" (format "%s..%s" branch1 branch2) :dir path)
    (:out)
    (str/split-lines)))

(defn- extract-tasks
  [lines]
  (reduce #(apply conj %1 (->>
                            (re-seq #"#(\d+)" %2)
                            (flatten)
                            (rest)
                            (take-nth 2)
                            )) (sorted-set) (filter #(re-find #"#\d+" %) lines)))

(defn- print-tasks
  [tasks]
  (doseq [task tasks] (println (str task-url task)))
  )

(defn -main
  [path branch1 branch2]
  (->
    (git-exec path branch1 branch2)
    (extract-tasks)
    (print-tasks)
    )
  (System/exit 0))
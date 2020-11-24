package com.devil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import com.devil.context.ApplicationContextListener;
import com.devil.util.Prompt;
import com.devil.web.filter.CommandFilterManager;
import com.devil.web.filter.DefaultCommandFilter;
import com.devil.web.filter.FilterChain;
import com.devil.web.filter.Request;
import com.devil.web.listener.AppInitListener;


public class App {

  // 옵저버와 공유할 맵 객체
  Map<String,Object> context = new Hashtable<>();

  // 옵저버를 보관할 컬렉션 객체
  List<ApplicationContextListener> listeners = new ArrayList<>();

  // 옵저버를 등록하는 메서드
  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  // 옵저버를 제거하는 메서드
  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  // service() 실행 전에 옵저버에게 통지한다.
  private void notifyApplicationContextListenerOnServiceStarted() {
    for (ApplicationContextListener listener : listeners) {
      // 곧 서비스를 시작할테니 준비하라고,
      // 서비스 시작에 관심있는 각 옵저버에게 통지한다.
      // => 옵저버에게 맵 객체를 넘겨준다.
      // => 옵저버는 작업 결과를 파라미터로 넘겨준 맵 객체에 담아 줄 것이다.
      listener.contextInitialized(context);
    }
  }

  // service() 실행 후에 옵저버에게 통지한다.
  private void notifyApplicationContextListenerOnServiceStopped() {
    for (ApplicationContextListener listener : listeners) {
      // 서비스가 종료되었으니 마무리 작업하라고,
      // 마무리 작업에 관심있는 각 옵저버에게 통지한다.
      // => 옵저버에게 맵 객체를 넘겨준다.
      // => 옵저버는 작업 결과를 파라미터로 넘겨준 맵 객체에 담아 줄 것이다.
      listener.contextDestroyed(context);
    }
  }


  public static void main(String[] args) throws Exception {
    App app = new App();

    // 옵저버 등록
    app.addApplicationContextListener(new AppInitListener());

    app.service();
  }

  public void service() throws Exception {

    notifyApplicationContextListenerOnServiceStarted();

    // 필터 관리자 준비
    CommandFilterManager filterManager = new CommandFilterManager();

    // 필터를 등록한다.
    //filterManager.add(new LogCommandFilter());
    //filterManager.add(new AuthCommandFilter());
    filterManager.add(new DefaultCommandFilter());


    // 필터들을 준비시킨다.
    filterManager.init(context);

    // 사용자가 입력한 명령을 처리할 필터 체인을 얻는다.
    FilterChain filterChain = filterManager.getFilterChains();

    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    loop:
      while (true) {
        String inputStr = Prompt.inputString("명령> ");

        if (inputStr.length() == 0) {
          continue;
        }

        commandStack.push(inputStr);
        commandQueue.offer(inputStr);

        switch (inputStr) {
          case "history": printCommandHistory(commandStack.iterator()); break;
          case "history2": printCommandHistory(commandQueue.iterator()); break;
          case "quit":
          case "exit":
            System.out.println("안녕!");
            break loop;
          default:
            // 커맨드나 필터가 사용할 객체를 준비한다.
            Request request = new Request(inputStr, context);

            // 필터들의 체인을 실행한다.
            if (filterChain != null) {
              filterChain.doFilter(request);
            }
        }
        System.out.println();
      }
    Prompt.close();

    // 필터들을 마무리시킨다.
    filterManager.destroy();

    notifyApplicationContextListenerOnServiceStopped();
  }

  void printCommandHistory(Iterator<String> iterator) {
    try {
      int count = 0;
      while (iterator.hasNext()) {
        System.out.println(iterator.next());
        count++;

        if ((count % 5) == 0 && Prompt.inputString(":").equalsIgnoreCase("q")) {
          break;
        }
      }
    } catch (Exception e) {
      System.out.println("history 명령 처리 중 오류 발생!");
    }
  }
}

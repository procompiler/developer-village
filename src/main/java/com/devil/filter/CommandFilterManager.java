package com.devil.filter;

import java.util.Map;
import com.devil.handler.Request;


// 역할:
// - CommandFilter 구현체를 관리하고 실행시킨다.
// -
public class CommandFilterManager {
  Chain firstChain;
  Chain lastChain;

  public void add(CommandFilter filter) {
    Chain chain = new Chain(filter);
    if (lastChain == null) {
      firstChain = lastChain = chain;
      return;
    }
    lastChain.nextChain = chain;
    lastChain = chain;
  }

  public FilterChain getFilterChains() {
    return firstChain;
  }

  // 각각의 필터에게 준비하라고 요청한다.
  public void init(Map<String,Object> context) throws Exception {
    Chain chain = firstChain;
    while (chain != null) {
      chain.filter.init(context);
      chain = chain.nextChain;
    }
  }

  //각각의 필터에게 마무리하라고 요청한다.
  public void destroy() {
    Chain chain = firstChain;
    while (chain != null) {
      chain.filter.destroy();
      chain = chain.nextChain;
    }
  }

  private static class Chain implements FilterChain {
    CommandFilter filter;
    Chain nextChain;

    public Chain(CommandFilter filter) {
      this.filter = filter;
    }

    @Override
    public void doFilter(Request request) throws Exception {
      filter.doFilter(request, nextChain);
    }
  }
}

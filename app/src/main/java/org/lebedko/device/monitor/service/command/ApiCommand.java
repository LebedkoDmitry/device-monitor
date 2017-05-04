package org.lebedko.device.monitor.service.command;

import java.io.Serializable;
import java.util.concurrent.Callable;

public interface ApiCommand<R> extends Callable<R>, Serializable {}
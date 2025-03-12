package org.mik.first.service;

import org.mik.first.domain.AbstractDomain;

import java.io.Serializable;

public abstract class AbstractService<ID extends Serializable, T extends AbstractDomain<ID>> {

}
